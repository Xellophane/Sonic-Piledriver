/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver;

import java.net.*;
import java.io.*;

/**
 *
 * @author Xellophane
 */
public class GreetingClient {
    String serverName = "GrumblerServer";
    int port = 600;
   Socket client;
    
    public void connect() {
        /* The connect method simply connects to the host server
        * and spits out a couple of error messages. should be able
        * to replace hard codded variables with options eventually.
        */
        try {
        System.out.println("Connecting to  " + serverName + " on port " + port);
        client = new Socket(serverName, port);
        System.out.println("Just connected to " + client.getRemoteSocketAddress());
        } catch (UnknownHostException e) {
            System.out.print("Unknown host " + e);
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("IOException : " + e);
            System.exit(-2);
        }
    }
    
    public void send() {
        
        /* The send method sends an output to the server, which 
        * should be sent back.
        */
        try {
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out =
                new DataOutputStream(outToServer);
    
        out.writeUTF("Hello from " + client.getLocalSocketAddress());
        InputStream inFromServer = client.getInputStream();
        DataInputStream in =
                new DataInputStream(inFromServer);
        System.out.println("Server says " + in.readUTF());
        client.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            e.printStackTrace();
        }
    }
}
