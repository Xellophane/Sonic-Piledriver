/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver;

import java.io.*;
import javax.sound.sampled.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.Arrays;

/**
 *
 * @author xellophane
 */
public class Speaker extends Thread {

    ByteBuffer buffer1;
    ByteBuffer buffer2;
    ByteBuffer bufferin;
    ByteBuffer bufferout;
    DataLine.Info info;
    SourceDataLine line;
    AudioFormat format;


    /*
     boolean running;
     ByteArrayInputStream in;
     int bufferSize;
     byte buffer[];
     DataLine.Info info;
     SourceDataLine line;
     AudioInputStream ais;
     */
    public Speaker(ByteBuffer b) throws LineUnavailableException {

        /*AudioFormat format = getFormat();
         bufferSize = (int) format.getSampleRate()
         * format.getFrameSize();
        
         in = new ByteArrayInputStream(buffer);
         ais = new AudioInputStream(in, format,
         buffer.length / format.getFrameSize());
         info = new DataLine.Info(SourceDataLine.class, format);
         line = (SourceDataLine) AudioSystem.getLine(info);
         line.open(format);
         line.start();
         */
        format = getFormat();

        bufferin = b;
        bufferout = ByteBuffer.allocate(b.capacity());
        buffer1 = ByteBuffer.allocate(b.capacity());
        buffer2 = ByteBuffer.allocate(b.capacity());
        info = new DataLine.Info(SourceDataLine.class, format);
        line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();

    }

    @Override
    public void run() {

        playAudio();
        line.drain();
        try {
            this.wait();
        } catch (InterruptedException e) {

        }

    }

    public void playAudio() {

        line.write(bufferout.array(), 0, bufferout.capacity());
        if (!buffer1.hasRemaining()) {
            bufferout = buffer2;
            bufferin = buffer1;
            buffer1.flip();
            buffer2.flip();
            buffer1.clear();
        } else {
            bufferout = buffer1;
            bufferin = buffer2;
            buffer1.flip();
            buffer2.flip();
            buffer2.clear();
        }

        /*int count;
         while ((count = ais.read(Arrays.copyOfRange(buffer1.array(), count, count))) != -1) {
         if (count > 0) {
         line.write(bufferin, count, count);
         }
                    
         } */
    }

    public void writeBuffer(ByteBuffer b) {

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
