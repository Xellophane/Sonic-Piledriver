/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import java.nio.channels.*;
import java.nio.*;

/**
 *
 * @author Xellophane
 */
public class Network {

    SocketChannel socketChannel;
    ServerSocketChannel Host;
    int port;
    Socket Server;
    Socket client;
    Microphone mic;
    Speaker speak;
    ByteBuffer input;
    ByteBuffer output;

    public Network(int port) throws IOException, LineUnavailableException {
        this.port = port;
        Host = ServerSocketChannel.open();
        Host.bind(new InetSocketAddress (port));
        Host.configureBlocking(false);
        
        System.out.println("Server Created and waiting "
                + "for connection on port " + port);
        mic = new Microphone();
        output = ByteBuffer.allocate(mic.bufferout.capacity());
        input = ByteBuffer.allocate(output.capacity());
        speak = new Speaker(input);
    }

    

    public void connect(InetAddress ip) throws IOException {
        socketChannel.connect(new InetSocketAddress(ip, port));
        socketChannel.configureBlocking(false);
    }
    
    public void Host(int port) throws IOException {
        if(socketChannel == null) {
            socketChannel = Host.accept();
            socketChannel.configureBlocking(false);
        }
    }

    public void send() throws IOException {
        if (socketChannel != null) {
            socketChannel.write(output);
        }
    }
    
    public void close() throws IOException {
        socketChannel.close();
    }

}
