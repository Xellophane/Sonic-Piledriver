/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 *
 * @author Cody
 */

/** To Do:
 * Maybe give all the interactive stuff (buttons, combobox, textfield)
 * their own variable names so we can track them easier?
 * Also: Action Listeners for the buttons and combobox need to be done.
 * @author Xellophane
 */
public class VoIPGUI extends JFrame{
    String hostName;
    int port;
    Socket client;
    Server host;
    Sound manager;
    boolean connected;
    
    
    
    
    public VoIPGUI() {
        //sets up VoIP menu GUI
        
        // LoginGUI login = new LoginGUI();
        // boolean verify = login.getVerify();
        
        
        Server host = new Server(6969);
        host.start();
        
        manager = new Sound();
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(300, 200));
        frame.setTitle("Java VoIP");
        frame.setLayout(new BorderLayout());
        JTextField field = new JTextField();
        JComboBox friendlist = new JComboBox();
        
        // sets up the lable, textfield, and friend list
        JPanel center = new JPanel(new GridLayout(2, 1));
        center.add(field);
        center.add(friendlist);
        frame.add(center, BorderLayout.CENTER);
        
        // Add two new buttons, Call, and End
        JPanel south = new JPanel(new FlowLayout());
        
        JButton callbt = new JButton("Call");
        ActionListener callListener = new ActionListener(){
                                 public void actionPerformed(ActionEvent e){
                                    try {
                                        client = connect(InetAddress.getByName(field.getText()), 6969);
                                        connected = true;
                                        DataOutputStream out = 
                                                new DataOutputStream(client.getOutputStream());
                                        DataInputStream in = new DataInputStream(host.server.getInputStream());
                                        manager.captureAudio();
                                        while (connected) {
                                            byte[] outbuffer = manager.outBuffer.toByteArray();
                                            out.write(outbuffer);
                                            manager.playAudio(in);
                                        }
                                    } catch (UnknownHostException f) {
                                        System.out.println("Uknown Host Error " + f);
                                    } catch (IOException g) {
                                        System.out.println("IOException " + g);
                                    }
                                } 
        };
        callbt.addActionListener(callListener);
        
        JButton addfriend = new JButton("Add Friend");
        /*ActionListener addfriendListener = new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    //Method that adds friends to a list for you
                                    //to call later
                                }
        }*/
        
        JButton endbt = new JButton("End");
        ActionListener endListener = new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    //method that ends the call
                                }
        };
        
        south.add(callbt);
        south.add(endbt);
        south.add(addfriend);
        frame.add(south, BorderLayout.SOUTH);
        
        JPanel west = new JPanel(new FlowLayout());
        west.add(new JLabel("Enter the IP you want to call:"));   
        frame.add(west, BorderLayout.NORTH);
        
        frame.setVisible(true);
        
        /*if(login.getVerify() == true){
            frame.setVisible(true);
        } else {
            frame.setVisible(false);
            System.out.println("Failed to verify login");
        } */
    }
    
    
    public Socket connect(InetAddress hostip, int port) {
        /* The connect method simply connects to the host server
        * and spits out a couple of error messages. should be able
        * to replace hard codded variables with options eventually.
        */
        try {
        System.out.println("Connecting to  " + hostip + " on port " + port);
        Socket outbound = new Socket(hostip, port);
        System.out.println("Just connected to " + outbound.getRemoteSocketAddress());
        return outbound;
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
