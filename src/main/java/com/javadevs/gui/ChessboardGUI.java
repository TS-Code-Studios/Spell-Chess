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

}

