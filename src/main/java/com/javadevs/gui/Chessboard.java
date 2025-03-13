package com.javadevs.gui;

//Developer: simonkdev
//Version: 0.1 10.03.2025
//add Information requests here:

import com.javadevs.chessButton;
import com.javadevs.ChessGameHandler;

import javax.swing;

public class Chessboard extends JPanel
{
    public Chessboard()
    {
        //FF
    }


    public buttonInit()
    {
        int count = 0;
        String countString = "";
       
        for(int i=0, i<16, i++)
        {
            countString = i;
            new ChessButton(countString);
        }
      }

    
}