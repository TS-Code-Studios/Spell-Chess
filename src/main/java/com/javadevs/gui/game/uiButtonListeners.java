package com.javadevs.gui.game;

import java.awt.event.ActionListener;

public class uiButtonListeners {
    
    public ActionListener BACK_BUTTON_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            // Handle back button action
            System.out.println("Back button pressed");
            // Add logic to navigate back or close the current window
        }
    };

    public ActionListener SETTINGS_BUTTON_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            // Handle settings button action
            System.out.println("Settings button pressed");
            // Add logic to open settings dialog or panel
        }
    };

    public ActionListener NEW_GAME_BUTTON_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            // Handle new game button action
            System.out.println("New Game button pressed");
            // Add logic to start a new game
        }
    };

    public ActionListener LOAD_GAME_BUTTON_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            // Handle load game button action
            System.out.println("Load Game button pressed");
            // Add logic to load a saved game
        }
    };



    public uiButtonListeners() {
        // Constructor can be used to initialize any required components or listeners
    }



}

