package com.javadevs.gui.game;

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
        button3 = new JButton("Save Game");
        button4 = new JButton("Exit");
        
        button1.setSize(100, 50);
        button2.setSize(100, 50);
        button3.setSize(100, 50);
        button4.setSize(100, 50);

        button1.setVisible(true);
        button2.setVisible(true);
        button3.setVisible(true);
        button4.setVisible(true);

        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);

    }
}
