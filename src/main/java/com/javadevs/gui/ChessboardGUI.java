package com.javadevs.gui;

//Developer: @simonkdev
//Version: 17/02/2025
//For more information check out INFORMATION.md

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.javadevs.ChessGameHandler;

//für position[][] gilt: position[y][x] (im Array)

public class ChessboardGUI extends JFrame;
{
  @SuppressWarnings("unused")
  ChessGameHandler game = new ChessGameHandler();
  JFrame chessboardWindow;
  List<chessButton> buttons;
  int count;
  int countRow;

  public ChessboardGUI () 
  {
    setVisible(true);
  }
    
  @SuppressWarnings("static-access") 						//Otherwise causes trouble in buttonInit() for using setSquareColor()
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

