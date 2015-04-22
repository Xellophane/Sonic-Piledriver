/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver;

import java.net.*;
import java.io.*;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author Xellophane
 */
public class Network extends Thread {

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

    public Network(int port) throws IOException, LineUnavailableException {
        this.port = port;
        Host = new ServerSocket(port);
        System.out.println("Server Created and waiting "
                + "for connection on port " + port);
        mic = new Microphone();
        speak = new Speaker(buffer);
    }

    @Override
    public void run() {
        boolean running = true;
        try {
            Server = Host.accept();
            in = Server.getInputStream();
            DataInputStream din = new DataInputStream(in);
            
            

            System.out.println(Server.getRemoteSocketAddress()
                    + " has just connected");
            int len = din.readInt();
            buffer = new byte[len];
            din.readFully(buffer);

            mic.start();
            speak.start();
            if (Server.isConnected()) {
                send(Server);
            } else if (client.isConnected()) {
                send(client);
            }

        } catch (SocketTimeoutException s) {
            s.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException p) {
            p.printStackTrace();
        }
    }

    public void connect(InetAddress ip) throws IOException {
        client = new Socket(ip, port);
    }

    public void send(Socket output) throws IOException {
        out = (ObjectOutputStream) output.getOutputStream();
        out.writeObject(mic.smallBuffer);
    }

}
