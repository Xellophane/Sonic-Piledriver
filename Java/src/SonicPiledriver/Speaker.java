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
public class Speaker extends Thread {

    boolean running;
    InputStream in;
    int bufferSize;
    byte buffer[];
    DataLine.Info info;
    SourceDataLine line;
    AudioInputStream ais;

    public Speaker(byte[] b) throws LineUnavailableException {

        AudioFormat format = getFormat();
        bufferSize = (int) format.getSampleRate()
                * format.getFrameSize();
        buffer = new byte[bufferSize];
        this.in = in;
        ais = new AudioInputStream(in, format,
                buffer.length / format.getFrameSize());
        info = new DataLine.Info(SourceDataLine.class, format);
        line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();

    }

    @Override
    public void run() {
        running = true;
        while(running) {
            playAudio();
        }
        line.drain();
        line.close();
    }

    public void playAudio() {
        try {
            int count;
            while ((count = ais.read(buffer, 0, buffer.length)) != -1) {
                if (count > 0) {
                    line.write(buffer, 0, count);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
