package com.javadevs.gui;

//Developer: simonkdev
//Version: 0.1 10.03.2025
//add Information requests here:

import com.javadevs.chessButton;
import com.javadevs.ChessGameHandler;

import javax.swing;

public class Chessboard extends JPanel
{
    List<chessButton> buttons;
    int count;
    int countRow;

    public Chessboard()
    {
       
    }
    
    public void buttonInit()
    {
      count = 0;
      columnInit("a");
      columnInit("b");
      columnInit("c");
      columnInit("d");
      columnInit("e");
      columnInit("f");
      columnInit("g");
      columnInit("h");
    }

  public void columnInit(String column)
    {
      for(int i=0; i<8; i++)
    {
      chessButton button = new chessButton( column, String.valueOf(countRow));
      buttons.add(button);
      countRow++;
      count++;
    }
    countRow = 1;
  }
    
}