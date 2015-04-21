/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver;

//import SonicPiledriver.DataBase;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Cody
 */
public class LoginGUI {
    private boolean verifyBoolean;
    public LoginGUI(){
        
        //Setting up Login GUI
        
        verifyBoolean = false;
        
        JFrame login = new JFrame();
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setSize(new Dimension(300, 150));
        login.setTitle("Login");
        
        login.setLayout(new BorderLayout());
        JPanel center = new JPanel(new GridLayout(2, 1));
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        center.add(username);
        center.add(password);
        login.add(center, BorderLayout.CENTER);
        
        JPanel west = new JPanel(new GridLayout(2, 1));
        west.add(new JLabel("Username:"));
        west.add(new JLabel("Password:"));
        login.add(west, BorderLayout.WEST);
        
        JPanel south = new JPanel(new FlowLayout());
        
        //Set an action listener to verify the user has an account and
        //allow access to the VoIP gui
        JButton loginbt = new JButton("Login");
        /*ActionListener loginListener = new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                DataBase.userLogin();
                            }
        };
        loginbt.addActionListener(loginListener);*/
        
        //Sets an action listener to register a new user
        JButton registerbt = new JButton("Register");
        ActionListener registerListener = new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                // RegisterGUI.Visible(true);
                            }
        };
        registerbt.addActionListener(registerListener);
        
        //Set an action listener to clear the text fields
        JButton clearbt = new JButton("Clear");
        /*ActionListener clearListener = new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                username.setText("");
                                password.setText("");
                            }
        };
        clearbt.addActionListener(clearListener);*/
        
        south.add(loginbt);
        south.add(registerbt);
        south.add(clearbt);
        login.add(south, BorderLayout.SOUTH);
        
        login.setVisible(true);
        
        
    }
    
    public boolean getVerify() {
        return verifyBoolean;
    }
}
