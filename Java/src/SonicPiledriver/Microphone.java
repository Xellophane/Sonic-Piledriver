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
public class Microphone extends Thread {

    int bufferSize;
    byte smallBuffer[];
    ByteArrayOutputStream out;
    boolean running;
    TargetDataLine line;
    DataLine.Info info;

    public Microphone() throws LineUnavailableException {
        AudioFormat format = getFormat();
        info = new DataLine.Info(TargetDataLine.class, format);
        line = (TargetDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();
        bufferSize = (int) format.getSampleRate() * format.getFrameSize();
        smallBuffer = new byte[bufferSize];
        out = new ByteArrayOutputStream();
    }

    @Override
    public void run() {
        synchronized (this) {
        
        captureAudio();
        try {
            this.wait();
        } catch (InterruptedException e) {

        }
    }
    }

    public void captureAudio() {
        int count
                = line.read(smallBuffer, 0, smallBuffer.length);
        if (count >= 0) {
            out.write(smallBuffer, 0, count);
        }
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
