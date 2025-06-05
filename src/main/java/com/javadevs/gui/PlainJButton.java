package com.javadevs.gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlainJButton extends JButton {

    public PlainJButton (String text){
        super(text);
        setBorder(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setBackground(Color.black);
    }

    // sample test method
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel pane = new JPanel();
        
        pane.setBorder(null);
        pane.setOpaque(false);

        pane.setBackground(Color.black);
    
        frame.setBackground(Color.black);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        pane.add(new PlainJButton("HI!!!!"));
        frame.add(pane);
        frame.pack();
        frame.setVisible(true);
    }
}