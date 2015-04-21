/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver;

import SonicPiledriver.SonicPiledriver.LoginGUI;

/**
 *
 * @author Xellophane
 */
public class Main {
    public static void main(String[] cl_args) {
        
        /* try {
            Thread server = new GreetingServer();
            server.start();
        } catch (IOException e) {
            System.out.println("IOException :" + e);
        } */
        
        // LoginGUI client = new LoginGUI();
        VoIPGUI gui = new VoIPGUI();
        // client.connect();
        // client.send();
    }
}
