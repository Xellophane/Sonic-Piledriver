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
public class Network {
    
    /**
     *
     * @param hostName
     * @param port
     * @return
     */
    public static Socket connect(InetAddress hostip, int port) {
        /* The connect method simply connects to the host server
        * and spits out a couple of error messages. should be able
        * to replace hard codded variables with options eventually.
        */
        try {
        System.out.println("Connecting to  " + hostip + " on port " + port);
        Socket client = new Socket(hostip, port);
        System.out.println("Just connected to " + client.getRemoteSocketAddress());
        return client;
        } catch (UnknownHostException e) {
            System.out.print("Unknown host " + e);
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("IOException : " + e);
            System.exit(-2);
        }
        
        return null;
    }
}
