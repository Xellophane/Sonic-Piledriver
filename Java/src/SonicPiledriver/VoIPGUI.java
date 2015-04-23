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
import java.util.HashMap;
import javax.swing.*;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author Cody
 */
/**
 * To Do: Maybe give all the interactive stuff (buttons, combobox, textfield)
 * their own variable names so we can track them easier? Also: Action Listeners
 * for the buttons and combobox need to be done.
 *
 * @author Xellophane
 */
public class VoIPGUI extends JFrame {

    String hostName;
    int port;
    Network network;
    String username;

    public VoIPGUI(String username) {
        //sets up VoIP menu GUI
        this.username = username;

        port = 6969;
        try {
            network = new Network(port);
        } catch (IOException e) {
            System.out.println("IOException " + e);
            e.printStackTrace();
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(300, 200));
        frame.setTitle("Java VoIP");
        frame.setLayout(new BorderLayout());
        JTextField field = new JTextField();

        //Creates the combobox with its action listeners
        JComboBox friendlist = new JComboBox();
        /*DataBase db = new DataBase();
         HashMap<String, String> buddies = db.getFriends();
         for (String s : buddies.keySet()) {
         friendlist.addItem(s);
         }
         ItemListener comboListener = new ItemListener() {
         public void itemStateChanged(ItemEvent e) {

         }
         };  */

        // sets up the lable, textfield, and friend list
        JPanel center = new JPanel(new GridLayout(2, 1));
        center.add(field);
        center.add(friendlist);
        frame.add(center, BorderLayout.CENTER);

        // Add two new buttons, Call, and End
        JPanel south = new JPanel(new FlowLayout());

        JButton callbt = new JButton("Call");
        ActionListener callListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    network.connect(InetAddress.getByName(field.getText()));
                } catch (UnknownHostException f) {
                    System.out.println("Uknown Host Error " + f);
                } catch (IOException g) {
                    System.out.println("IOException " + g);
                } catch (NullPointerException n) {
                    System.out.println("NullPointerException " + n);
                    n.printStackTrace();
                }
            }
        };
        callbt.addActionListener(callListener);

        JButton addfriend = new JButton("Add Friend");
        /* ActionListener addfriendListener = new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         new AddFriendGUI();
         }
         }; */

        JButton endbt = new JButton("End");
        ActionListener endListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

    }

}
