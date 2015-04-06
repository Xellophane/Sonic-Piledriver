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
public class VoIPGUI extends JFrame{
    public static void main(String[] args) {
        
        //sets up VoIP menu GUI
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(300, 200));
        frame.setTitle("Java VoIP");
        
        frame.setLayout(new BorderLayout());
        JPanel west = new JPanel(new GridLayout(2,1));
        west.add(new JButton("Call"));
        west.add(new JButton("End"));
        frame.add(west, BorderLayout.WEST);
        
        JPanel center = new JPanel(new GridLayout(3,1));
        center.add(new JLabel("Enter IP you want to call:"));
        center.add(new JTextField());
        center.add(new JComboBox());
        frame.add(center, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
    
}
