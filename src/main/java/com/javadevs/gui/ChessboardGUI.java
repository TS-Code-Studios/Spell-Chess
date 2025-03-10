package com.javadevs.gui;

//Developer: @simonkdev
//Version: 17/02/2025
//For more information check out INFORMATION.md

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.javadevs.ChessGameHandler;

//für position[][] gilt: position[y][x] (im Array)

public class ChessboardGUI 
{
  @SuppressWarnings("unused")
  ChessGameHandler game = new ChessGameHandler();
  JFrame chessboardWindow;
  List<chessButton> buttons;

  public ChessboardGUI () 
  {
    chessboardWindow = new JFrame();
    chessboardWindow.setVisible(true);
    buttons = new ArrayList<>();
  }
    
  @SuppressWarnings("static-access") 						//Otherwise causes trouble in buttonInit() for using setSquareColor()
public void buttonInit()
  {
    int count = 0;
    int countA = 1;

    //creates buttons for column a
    for(int i=0; i<8; i++)
    {
      chessButton button = new chessButton("a", String.valueOf(countA));
      buttons.add(button);
      countA++;
      count++;
    }

    // Call setSquareColor on each button
    for (chessButton button : buttons) {
      button.setSquareColor();
    }
  }
}

