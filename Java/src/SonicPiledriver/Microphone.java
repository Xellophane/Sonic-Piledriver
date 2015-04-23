/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver;

import java.io.*;
import javax.sound.sampled.*;
import java.nio.*;

/**
 *
 * @author xellophane
 */
public class Microphone extends Thread {

    /*int bufferSize;
    byte smallBuffer[];
    ByteArrayOutputStream out;
    boolean running;
    TargetDataLine line;
    DataLine.Info info;
    */
    
    int bufferSize;
    ByteBuffer bufferin;
    ByteBuffer bufferout;
    ByteBuffer buffer1;
    ByteBuffer buffer2;
    DataLine.Info info;
    TargetDataLine line;
    AudioFormat format;
    

    public Microphone() throws LineUnavailableException {
        /*
        AudioFormat format = getFormat();
        info = new DataLine.Info(TargetDataLine.class, format);
        line = (TargetDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();
        bufferSize = (int) format.getSampleRate() * format.getFrameSize();
        smallBuffer = new byte[bufferSize];
        out = new ByteArrayOutputStream();
        */
        
        format = getFormat();
        bufferSize = (int) format.getSampleRate() * format.getFrameSize();
        bufferin = ByteBuffer.allocate(bufferSize);
        bufferout = ByteBuffer.allocate(bufferSize);
        buffer1 = ByteBuffer.allocate(bufferSize);
        buffer2 = ByteBuffer.allocate(bufferSize);
        info = new DataLine.Info(SourceDataLine.class, format);
        line = (TargetDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();
    }

    @Override
    public void run() {
        
        /*
        synchronized (this) {
        
        captureAudio();
        try {
            this.wait();
        } catch (InterruptedException e) {

        }
    } */
        
        captureAudio();
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    public void captureAudio() {
        
        /*
        int count
                = line.read(smallBuffer, 0, smallBuffer.length);
        if (count >= 0) {
            out.write(smallBuffer, 0, count);
        }
        */
        
        line.read(bufferout.array(), bufferout.position(), bufferout.limit());
        if (bufferout.position() == bufferout.limit() && bufferout.equals(buffer1)) {
            bufferout = buffer2;
            buffer1.flip();
            bufferin = buffer1;
        } else if (bufferout.position() == bufferout.limit() && bufferout.equals(buffer2)){
            bufferout = buffer1;
            buffer2.flip();
            bufferin = buffer2;
        }
    }
    
    public void clearBuffer() {
        bufferin.clear();
    }

    private AudioFormat getFormat() {
        float sampleRate = 8000;
        int sampleSizeInBits = 8;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate, sampleSizeInBits,
                channels, signed, bigEndian);
    }

}
