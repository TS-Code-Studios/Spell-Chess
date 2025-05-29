package com.javadevs.gui.game;

import com.javadevs.gui.game.uiButtonListeners;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Icon;

public class bottomBar extends JPanel
{
    JButton ABORT_BUTTON;
    JButton UNDO_BUTTON;

    Icon ABORT_ICON = new javax.swing.ImageIcon("src/main/resources/icons/abort_button.png");
    Icon UNDO_ICON = new javax.swing.ImageIcon("src/main/resources/icons/undo_button.png");

    Icon ABORT_PRESSED_ICON = new javax.swing.ImageIcon("src/main/resources/icons/abort_button_pressed.png");
    Icon UNDO_PRESSED_ICON = new javax.swing.ImageIcon("src/main/resources/icons/undo_button_pressed.png");

    
    uiButtonListeners LIS_LIB = new uiButtonListeners();

    public bottomBar()
    {
        setSize(400, 33);
        setBackground(Color.black);
        setLayout(new GridLayout(1, 4)); // 1 row, 4 columns
        load_buttons_bar();
        setOpaque(false);
        setVisible(true);
    }
    
    public void load_buttons_bar()
    {
        ABORT_BUTTON = new JButton();
        UNDO_BUTTON = new JButton();
        
        ABORT_BUTTON.setSize(180, 33);
        UNDO_BUTTON.setSize(180, 33);

        ABORT_BUTTON.setIcon(ABORT_ICON);
        UNDO_BUTTON.setIcon(UNDO_ICON);

        ABORT_BUTTON.setContentAreaFilled(false);
        ABORT_BUTTON.setOpaque(false);
        
        UNDO_BUTTON.setContentAreaFilled(false);
        UNDO_BUTTON.setOpaque(false);

        ABORT_BUTTON.setBorderPainted(false);
        UNDO_BUTTON.setBorderPainted(false);

        ABORT_BUTTON.setFocusPainted(false);
        UNDO_BUTTON.setFocusPainted(false);

        ABORT_BUTTON.setVisible(true);
        UNDO_BUTTON.setVisible(true);

        ABORT_BUTTON.addActionListener(ABORT_BUTTON_LISTENER);
        UNDO_BUTTON.addActionListener(UNDO_BUTTON_LISTENER);

        this.add(ABORT_BUTTON);
        this.add(UNDO_BUTTON);

    }

    //------------------------------------------------------------------BUTTON LISTENERS------------------------------------------------------------------//


    //     public ActionListener BACK_BUTTON_LISTENER = new ActionListener() {
    //     @Override
    //     public void actionPerformed(java.awt.event.ActionEvent e) {
    //         // Handle back button action
    //         System.out.println("Back button pressed");
    //         // Add logic to navigate back or close the current window
    //     }
    // };

    // public ActionListener SETTINGS_BUTTON_LISTENER = new ActionListener() {
    //     @Override
    //     public void actionPerformed(java.awt.event.ActionEvent e) {
    //         // Handle settings button action
    //         System.out.println("Settings button pressed");
    //         // Add logic to open settings dialog or panel
    //     }
    // };

    public ActionListener ABORT_BUTTON_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            // Handle new game button action
            abort_pressed();
            // Add logic to start a new game
        }
    };

    public ActionListener UNDO_BUTTON_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            // Handle load game button action
            undo_pressed();
            // Add logic to load a saved game
        }
    };

//------------------------------------------------------------------BUTTON CALLED FUNCTIONS------------------------------------------------------------------//

public void abort_pressed()
    {
        ABORT_BUTTON.setIcon(ABORT_PRESSED_ICON); // Show pressed icon immediately

        // Use a Swing Timer to reset the icon after 1 second (1000 ms)
        new javax.swing.Timer(100, e -> {
            ABORT_BUTTON.setIcon(ABORT_ICON);
            ((javax.swing.Timer) e.getSource()).stop();
        }).start();

        
        //Imagine an abort function, please add confirmation overlay
    }

public void undo_pressed()
    {
        UNDO_BUTTON.setIcon(UNDO_PRESSED_ICON); // Show pressed icon immediately

        // Use a Swing Timer to reset the icon after 1 second (1000 ms)
        new javax.swing.Timer(100, e -> {
            UNDO_BUTTON.setIcon(UNDO_ICON);
            ((javax.swing.Timer) e.getSource()).stop();
        }).start();

        //Imagine an undo function, please add confirmation overlay
    }




}
