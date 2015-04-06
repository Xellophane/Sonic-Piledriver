/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
/**
 *
 * @author Cody
 */

/** To Do:
 * Maybe give all the interactible stuff (buttons, combobox, textfield)
 * their own variable names so we can track them easier?
 * Also: Action Listeners for the buttons and combobox need to be done.
 * @author Xellophane
 */
public class VoIPGUI extends JFrame{
    String hostName;
    int port;
    Socket client;
    
    public VoIPGUI() {
        //sets up VoIP menu GUI
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(300, 200));
        frame.setTitle("Java VoIP");
        JTextField field = new JTextField();
        JComboBox history = new JComboBox();
        
        // Add two new buttons, Call, and End
        frame.setLayout(new BorderLayout());
        JPanel west = new JPanel(new GridLayout(2,1));
        west.add(new JButton("Call"));
        west.add(new JButton("End"));
        frame.add(west, BorderLayout.WEST);
        
        // sets up the lable, textfield, and combobox
        JPanel center = new JPanel(new GridLayout(3,1));
        center.add(new JLabel("Enter IP you want to call:"));
        center.add(field);
        center.add(history);
        frame.add(center, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
    
    
}
