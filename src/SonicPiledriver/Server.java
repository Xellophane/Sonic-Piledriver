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
    public boolean running = true;
    Socket server;
    
    public Server(int port) {
        this.port = port;
        try {
            Socket = new ServerSocket(port);
            System.out.println("Waiting for connection on port " + port);
            
        } catch (IOException e) {
            System.out.println("IOException " + e);
            e.printStackTrace();
        }
        System.out.println("Server created");
        
    }
    
    public void run() {
        while (running) {
            try {
                
                server = Socket.accept();
                System.out.println(server.getRemoteSocketAddress() + 
                        " has just connected");
            } catch(SocketTimeoutException s) {
                System.out.println("Socket timed out");
                break;
            } catch (IOException e) {
                System.out.println("IOException " + e);
                e.printStackTrace();
                break;
            } catch (NullPointerException p) {
                System.out.println("NullPointerException " + p);
                p.printStackTrace();
            }
        }
    }
    
    
    // getter methods
    public ServerSocket getSocket() {
        return Socket;
    }
    
    
}
