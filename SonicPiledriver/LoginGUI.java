/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Cody
 */
public class LoginGUI {
    public LoginGUI(){
        
        //Setting up Login GUI
        
        JFrame login = new JFrame();
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setSize(new Dimension(300, 150));
        login.setTitle("Login");
        
        login.setLayout(new BorderLayout());
        JPanel center = new JPanel(new GridLayout(2, 1));
        center.add(new JTextField());
        center.add(new JTextField());
        login.add(center, BorderLayout.CENTER);
        
        JPanel west = new JPanel(new GridLayout(2, 1));
        west.add(new JLabel("Username:"));
        west.add(new JLabel("Password:"));
        login.add(west, BorderLayout.WEST);
        
        JPanel south = new JPanel(new FlowLayout());
        south.add(new JButton("Login"));
        south.add(new JButton("Register"));
        south.add(new JButton("Cancel"));
        login.add(south, BorderLayout.SOUTH);
        
        login.setVisible(true);
        
        
    }
}
