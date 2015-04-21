/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver.SonicPiledriver;

import java.net.*;
import java.io.*;

/**
 *
 * @author Xellophane
 */
public class GreetingServer extends Thread {
    private ServerSocket serverSocket;
    
    public GreetingServer() throws IOException {
        serverSocket = new ServerSocket(600);
        serverSocket.setSoTimeout(10000);
    }
    
    public void run(){
        while (true) {
            try {
                System.out.println("Waiting for client on port " +
                        serverSocket.getLocalPort()+ "...");
                Socket server = serverSocket.accept();
                System.out.println(server.getRemoteSocketAddress() +
                        " has just connected");
                DataInputStream in =
                        new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out =
                        new DataOutputStream(server.getOutputStream());
                out.writeUTF(" Thank you for connecting to " +
                        server.getLocalSocketAddress() + "\nGoodbye!");
                server.close();
            } catch(SocketTimeoutException s) {
                System.out.println("socket timed out!");
                break;
            } catch (IOException e) {
                System.out.println("IOException: " + e);
                e.printStackTrace();
                break;
            }
        }
    }
    
}
