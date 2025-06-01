package com.javadevs.gui.game;
import com.javadevs.gui.game.uiButtonListeners;



import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class topBar extends JPanel 
{ 
    Icon BACK_ICON = new ImageIcon("src/main/resources/icons/back.png");
    Icon SETTINGS_ICON = new ImageIcon("src/main/resources/icons/settings.png");

    Icon TOP_LOGO_ICON = new ImageIcon("src/main/resources/icons/top_logo.png");

    uiButtonListeners LIS_LIB = new uiButtonListeners();

    JButton backButton;
    JButton settingsButton;
    Dimension BAR_SPACER = new Dimension(260, 0); // Space between the two buttons

    public topBar() {
        setSize(new Dimension(400, 30));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); // Use BoxLayout for proper spacing
        load_buttons_bar();
        setOpaque(false); // Make sure background is transparent for future-proofing
        setVisible(true);
        //Hi 
    }

    public void load_buttons_bar() {
        backButton = new JButton();
        settingsButton = new JButton();

        backButton.setPreferredSize(new Dimension(30, 30));
        settingsButton.setPreferredSize(new Dimension(30, 30));

        backButton.setIcon(BACK_ICON);
        settingsButton.setIcon(SETTINGS_ICON);

        backButton.setContentAreaFilled(false);
        backButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setOpaque(false);

        backButton.setBorderPainted(false);
        settingsButton.setBorderPainted(false);

        backButton.setFocusPainted(false);
        settingsButton.setFocusPainted(false);

        // backButton.addActionListener(LIS_LIB.BACK_BUTTON_LISTENER);
        // settingsButton.addActionListener(LIS_LIB.SETTINGS_BUTTON_LISTENER);
        
        JPanel SPACER_PANEL = new JPanel();
        SPACER_PANEL.setPreferredSize(BAR_SPACER);
        SPACER_PANEL.setOpaque(false); // Make sure the spacer is transparent
        SPACER_PANEL.setVisible(true);
        SPACER_PANEL.setLayout(new GridLayout());
        
        JButton TOP_LOGO_PLACEHOLDER = new JButton();
        TOP_LOGO_PLACEHOLDER.setContentAreaFilled(false);
        TOP_LOGO_PLACEHOLDER.setOpaque(false);
        TOP_LOGO_PLACEHOLDER.setBorderPainted(false);
        TOP_LOGO_PLACEHOLDER.setFocusPainted(false);
        TOP_LOGO_PLACEHOLDER.setVisible(true); // Placeholder for future logo
        TOP_LOGO_PLACEHOLDER.setIcon(TOP_LOGO_ICON);
        
        SPACER_PANEL.add(TOP_LOGO_PLACEHOLDER); // Add a placeholder for future logo
        


        this.add(backButton);
        this.add(SPACER_PANEL); // Invisible, future-proof spacer
        this.add(settingsButton);
    }
}
