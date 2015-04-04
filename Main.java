/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver;

import java.io.*;

/**
 *
 * @author Xellophane
 */
public class Main {
    public static void main(String[] cl_args) {
        
        while(!false) {
            System.out.println("Ur face!");
        }
        
        try {
            Thread server = new GreetingServer();
            server.start();
        } catch (IOException e) {
            System.out.println("IOException :" + e);
        }
        
        GreetingClient client = new GreetingClient();
        client.connect();
        client.send();
    }
}
