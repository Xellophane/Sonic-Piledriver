/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Cody
 */
public class RegisterGUI {
    
    
    public RegisterGUI(){
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(300, 200));
        frame.setTitle("Register");
        frame.setLayout(new BorderLayout());
        
        //Creates and adds the textfields used to register a new user
        JPanel center = new JPanel(new GridLayout(3,1));
        JTextField username = new JTextField();
        JTextField password = new JTextField();
        JTextField ip = new JTextField();
        center.add(username);
        center.add(password);
        center.add(ip);
        frame.add(center, BorderLayout.CENTER);
        
        //Adds the buttons to the bottom of the panel
        JPanel south = new JPanel(new FlowLayout());
        JButton create = new JButton();
        JButton cancel = new JButton();
        south.add(create);
        south.add(cancel);
        frame.add(south, BorderLayout.SOUTH);
        
        //Puts labels on the side of the text boxes
        JPanel west = new JPanel(new GridLayout(3,1));
        JLabel Username = new JLabel("Username:");
        JLabel Password = new JLabel("Password:");
        JLabel IP = new JLabel("IP address:");
        west.add(Username);
        west.add(Password);
        west.add(IP);
        frame.add(west, BorderLayout.WEST);
        
        //Setting up action listeners
        /*ActionListener createListener = new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    //uses the write method for the database
                                }
        };
        create.addActionListener(createListener);
                */
        
        ActionListener cancelListener = new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    Visible(false);
                                }
        }
        
    }
    
    public static boolean Visible(boolean v){
        if(v == true){
            frame.setVisible(true);
            
        }
    }
    
}
