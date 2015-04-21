/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver;

/**
 *
 * @author xellophane
 */

import java.net.*;
import java.io.*;


public class Server extends Thread {
    private ServerSocket Socket;
    private int port;
    private boolean running = false;
    Socket server;
    
    public Server(int port) {
        try {
            Socket = new ServerSocket(port);
            Socket.setSoTimeout(10000);
            
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
        System.out.println("Server created");
        
    }
    
    public void run() {
        while (running) {
            try {
                System.out.println("Waiting for connection on port " + port);
                server = Socket.accept();
                System.out.println(server.getRemoteSocketAddress() + 
                        " has just connected");
                DataInputStream in =
                        new DataInputStream(server.getInputStream());
                server.close();
            } catch(SocketTimeoutException s) {
                System.out.println("Socket timed out");
                break;
            } catch (IOException e) {
                System.out.println("IOException " + e);
                break;
            }
        }
    }
    
    
    // getter methods
    public ServerSocket getSocket() {
        return Socket;
    }
}
