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
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Cody
 */
public class AddFriendGUI {
    
    public AddFriendGUI(){
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(300, 200));
        frame.setTitle("Add Friend");
        frame.setLayout(new BorderLayout());
        JTextField username = new JTextField();
        JTextField friendname = new JTextField();
        JLabel Username = new JLabel("Your Username:");
        JLabel Friendname = new JLabel("Friends name:");
        
        JPanel center = new JPanel(new GridLayout(2,1));
        center.add(username);
        center.add(friendname);
        frame.add(center, BorderLayout.CENTER);
        
        JPanel west = new JPanel(new GridLayout(2, 1));
        west.add(Username);
        west.add(Friendname);
        frame.add(west, BorderLayout.WEST);
        
        //Adds buttons with their action listeners
        JPanel south = new JPanel(new FlowLayout());
        
        JButton addbt = new JButton("Add Friend");
        ActionListener addListener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    DataBase db = new DataBase();
                    db.addFriend(username.getText(), friendname.getText());
                    db.killDB();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        addbt.addActionListener(addListener);
        south.add(addbt);
        
        JButton removebt = new JButton("Remove Friend");
        /* ActionListener removeListener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                DataBase db = new DataBase();
                db.removeFriend(username.getText(), friendname.getText());
            }
        };
        removebt.addActionListener(removeListener);
        south.add(removebt); */
                
        JButton exitbt = new JButton("Exit");
        ActionListener exitListener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        };
        exitbt.addActionListener(exitListener);
        south.add(exitbt);
        
    }
    
}
