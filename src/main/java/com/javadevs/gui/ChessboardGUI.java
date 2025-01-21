package com.javadevs.gui;

//Developer: @FRBF Studios
//Version: 15/1/2024
//For more information check out INFORMATION.md

import javax.swing.JFrame;

import com.javadevs.ChessGameHandler;

public class ChessboardGUI {
  @SuppressWarnings("unused")
  ChessGameHandler game = new ChessGameHandler();
  JFrame chessboardWindow;


  public ChessboardGUI () {
    chessboardWindow = new JFrame();
    chessboardWindow.setVisible(true);
  }
}