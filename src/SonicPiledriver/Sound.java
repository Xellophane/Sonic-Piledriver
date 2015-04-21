/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver;

import java.io.*;
import javax.sound.sampled.*;

/**
 *
 * @author xellophane
 */
public class Sound {
    boolean running;
    ByteArrayOutputStream outBuffer;
    ByteArrayInputStream inBuffer;
    
    public void captureAudio() {
        try {
            AudioFormat format = getFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            TargetDataLine line = (TargetDataLine)
                    AudioSystem.getLine(info);
            line.open(format);
            line.start();
            Runnable runner = new Runnable() {
                int bufferSize = (int)format.getSampleRate() * format.getFrameSize();
                byte buffer[] = new byte[bufferSize];
                
                public void run() {
                    outBuffer = new ByteArrayOutputStream();
                    running = true;
                    try {
                        while (running) {
                            int count =
                                    line.read(buffer, 0, buffer.length);
                            if (count >= 0) {
                                outBuffer.write(buffer, 0, count);
                            }
                        }
                        outBuffer.close();
                    } catch (IOException e) {
                        System.err.println("I/O problems: " + e);
                        e.printStackTrace();
                        System.exit(-1);
                    }
                }
            };
            Thread captureThread = new Thread(runner);
            captureThread.start();
        } catch (LineUnavailableException e) {
            System.err.println("Line unavailable: " + e);
            System.exit(-2);
        }
    }
    
    public void playAudio(DataInputStream in) {
        try {
            AudioFormat format = getFormat();
            int audioSize = (int) format.getSampleRate()
                    * format.getFrameSize();
            byte audio[] = new byte[audioSize];
            InputStream input =
                    new ByteArrayInputStream(audio);
            
            AudioInputStream ais =
                    new AudioInputStream(input, format, audio.length / format.getFrameSize());
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine line = (SourceDataLine)
                AudioSystem.getLine(info);
            line.open(format);
            line.start();
            
            Runnable runner = new Runnable() {
                int bufferSize = (int) format.getSampleRate()
                        * format.getFrameSize();
                byte buffer[] = new byte[bufferSize];
                
                public void run() {
                    try {
                        int count;
                        while((count = ais.read(
                        buffer, 0, buffer.length)) != -1) {
                            if (count > 0) {
                                line.write(buffer, 0, count);    
                            }
                        }
                        line.drain();
                        line.close();
                        
                    } catch (IOException e) {
                        System.err.println("I/O problems: " + e);
                    }
                }
            };
            Thread playThread = new Thread(runner);
            playThread.start();
        } catch (LineUnavailableException e) {
            System.err.println("Line unavailable: " + e);
        }
    }
    
    
    private AudioFormat getFormat() {
        float sampleRate = 8000;
        int sampleSizeInBits = 8;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate,
        sampleSizeInBits, channels, signed, bigEndian);
    }
}
