package com.javadevs.gui.game;
import com.javadevs.gui.game.uiButtonListeners;



import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class topBar extends JPanel 
{ 

    uiButtonListeners LIS_LIB = new uiButtonListeners();

    JButton backButton;
    JButton settingsButton;
    Dimension BAR_SPACER = new Dimension(200, 0); // Space between the two buttons

    public topBar() {
        setPreferredSize(new Dimension(400, 50));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); // Use BoxLayout for proper spacing
        load_buttons_bar();
        setOpaque(false); // Make sure background is transparent for future-proofing
        setVisible(true);
    }

    public void load_buttons_bar() {
        backButton = new JButton("Back");
        settingsButton = new JButton("Settings");

        backButton.setPreferredSize(new Dimension(100, 50));
        settingsButton.setPreferredSize(new Dimension(100, 50));

        backButton.setContentAreaFilled(false);
        backButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setOpaque(false);

        backButton.setBorderPainted(false);
        settingsButton.setBorderPainted(false);

        backButton.setFocusPainted(false);
        settingsButton.setFocusPainted(false);

        backButton.addActionListener(LIS_LIB.BACK_BUTTON_LISTENER);
        settingsButton.addActionListener(LIS_LIB.SETTINGS_BUTTON_LISTENER);
        

        this.add(backButton);
        this.add(Box.createRigidArea(BAR_SPACER)); // Invisible, future-proof spacer
        this.add(settingsButton);
    }
}
