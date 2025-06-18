package com.javadevs.gui.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.DigestException;

import com.javadevs.gui.game.ChessboardGUI;
import com.javadevs.gui.main.*;

public class NGButton extends JButton
{

    Dimension BUTTON_SIZE;
    ActionListener ON_PRESSED;

    MainMenuGUI MAIN_MENU_FRAME;

    public int BUTTON_HEIGHT = 50;

//    Icon NG_ICON new ImageIcon("src/main/resources/ng_button.png")

    public NGButton(MainMenuGUI menuFrame)
    {
        BUTTON_SIZE = new Dimension((menuFrame.FRAME_WIDTH / 2), BUTTON_HEIGHT);
        System.out.println(BUTTON_SIZE);

        ON_PRESSED = new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                ng_pressed();
            }
        };
        addActionListener(ON_PRESSED);

        MAIN_MENU_FRAME = menuFrame;

        setSize(menuFrame.FRAME_WIDTH / 2, BUTTON_HEIGHT);
        setPreferredSize(BUTTON_SIZE);
        setMinimumSize(BUTTON_SIZE);
        setMaximumSize(BUTTON_SIZE);

//        setIcon(NG_ICON);
        setOpaque(true);
        setVisible(true);
        setFocusPainted(false);
        setBorderPainted(false);

    }

    public void ng_pressed()
    {
        ChessboardGUI MAIN_WINDOW = new ChessboardGUI();
        MAIN_WINDOW.init_main_window();
        // System.out.println("NG Button Pressed");
        MAIN_MENU_FRAME.dispose();
    }





    
}
