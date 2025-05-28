package com.javadevs.gui.game;

import com.javadevs.gui.game.uiButtonListeners;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class bottomBar extends JPanel
{
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    
    uiButtonListeners LIS_LIB = new uiButtonListeners();

    public bottomBar()
    {
        setSize(400, 50);
        setBackground(Color.black);
        setLayout(new GridLayout(1, 4)); // 1 row, 4 columns
        load_buttons_bar();
        setVisible(true);
    }
    
    public void load_buttons_bar()
    {
        button1 = new JButton("New Game");
        button2 = new JButton("Load Game");
        
        button1.setSize(200, 50);
        button2.setSize(200, 50);

        button1.setContentAreaFilled(false);
        button1.setOpaque(false);
        
        button2.setContentAreaFilled(false);
        button2.setOpaque(false);

        button1.setBorderPainted(false);
        button2.setBorderPainted(false);

        button1.setFocusPainted(false);
        button2.setFocusPainted(false);

        button1.setVisible(true);
        button2.setVisible(true);

        button1.addActionListener(LIS_LIB.NEW_GAME_BUTTON_LISTENER);
        button2.addActionListener(LIS_LIB.LOAD_GAME_BUTTON_LISTENER);

        this.add(button1);
        this.add(button2);

    }
}
