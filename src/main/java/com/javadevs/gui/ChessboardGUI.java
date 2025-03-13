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
  List<cheessButton> buttonsA;
  List<chessButton> buttonsB;
  List<chessButton> buttonsC;
  List<chessButton> buttonsD;
  List<chessButton> buttonsE;
  List<chessButton> buttonsF;
  List<chessButton> buttonsG;
  List<chessButton> buttonsH;

  public ChessboardGUI () 
  {
    setVisible(true);
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

    //creates buttons for column b
    for(int i=0; i<8; i++)
    {
      chessButton button = new chessButton("b",String.valueOf(countA));
      buttons.add(button);
      countA++;
      count++;
    }

    //creates buttons for column c
    for(int i=0; i<8; i++)
    {
      chessButton button = new chessButton("c", String.valueOf(countA));
      buttons.add(button)
      countA++;
      count++;
    }

    //creates buttons for column d
    for(int i=0; i<8; i++)
    {
      chessButton button = new chessButton("d", String.valueOf(countA));
      buttons.add(button);
      countA++;
      count++;
    }

    //creates buttons for column e
    for(int i=0; i<8; i++)
    {
      chessButton button = new chessButton("e", String.valueOf(countA));
      buttons.add(button);
      countA++;
      count++;
    }

    //creates buttons for column f
    for(int i=0; i<8; i++)
    {
      chessButton button = new chessButton("f", String.valueOf(countA));
      buttons.add(button);
      countA++;
      count++;
    }

    //creates buttons for column g 
    for(int i=0; i<8; i++)
    {
      chessButton button = new chessButton("g", String.valueOf(countA));
      buttons.add(button);
      countA++;
      count++;
    }

    //creates buttons for column h
    for(int i=0; i<8; i++)
    {
      chessButton button = new chessButton("h", String.valueOf(countA));
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

