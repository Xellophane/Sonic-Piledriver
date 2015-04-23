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

/**
 *
 * @author Xellophane
 */
public class Network {

    ServerSocket Host;
    int port;
    boolean running;
    Socket Server;
    Socket client;
    Microphone mic;
    Speaker speak;
    InputStream in;
    ObjectOutputStream out;
    byte buffer[];

    public Network(int port) throws IOException {
        this.port = port;
        Host = new ServerSocket(port);
        System.out.println("Server Created and waiting "
                + "for connection on port " + port);
        running = true;
    }

    public void run() {

        try {

            Server = Host.accept();
            in = Server.getInputStream();
            DataInputStream din = new DataInputStream(in);

            System.out.println(Server.getRemoteSocketAddress()
                    + " has just connected");
            int len = din.readInt();
            buffer = new byte[len];
            din.readFully(buffer);

            mic = new Microphone();
            speak = new Speaker(buffer);
            mic.start();
            speak.start();

            while (running) {
                mic.notify();
                speak.notify();
                if (Server.isConnected()) {
                    send(Server);
                } else if (client.isConnected()) {
                    send(client);
                } else if (!Server.isConnected() && !client.isConnected()) {
                    
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void connect(InetAddress ip) throws IOException {
        client = new Socket(ip, port);
    }
    
    public void Host(int port) throws IOException {
        InetSocketAddress name = new InetSocketAddress(port);
        Host.bind(name);
        
    }

    public void send(Socket output) throws IOException {
        out = (ObjectOutputStream) output.getOutputStream();
        out.writeObject(mic.smallBuffer);
    }

}
