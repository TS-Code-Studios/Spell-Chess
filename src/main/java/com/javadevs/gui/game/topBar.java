package com.javadevs.gui.game;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class topBar extends JPanel 
{ 

    JButton backButton;
    JButton settingsButton;
    Dimension BAR_SPACER = new Dimension(200, 0); // Space between the two buttons

    public topBar() {
        setSize(400, 50);
        this.setLayout(new GridLayout(1, 3)); // 1 row, 3 columns
        load_buttons_bar();
        setVisible(true);
    }

    public void load_buttons_bar() {
        backButton = new JButton("Back");
        settingsButton = new JButton("Settings");

        backButton.setSize(100, 50);
        settingsButton.setSize(100, 50);

        backButton.setVisible(true);
        settingsButton.setVisible(true);

        this.add(backButton);
        this.add(Box.createRigidArea(BAR_SPACER)); // Add space between the buttons
        this.add(settingsButton);
        
        
    }


}
