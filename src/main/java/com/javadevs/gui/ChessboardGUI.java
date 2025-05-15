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
  ChessBoard board = new ChessBoard();
  ChessGameHandler gameHandler = new ChessGameHandler();

  @SuppressWarnings("unused")

  public ChessboardGUI () 
  {
    board.ChessBoard();
    add(board);
    setTitle("Chessboard");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 800);
    setResizable(false);
    setVisible(true);
  }
    
  @SuppressWarnings("static-access") 						//Otherwise causes trouble in buttonInit() for using setSquareColor()

}

