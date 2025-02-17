package com.javadevs.gui;

//Developer: @simonkdev
//Version: 17/02/2025
//For more information check out INFORMATION.md

import javax.swing.JFrame;

import com.javadevs.ChessGameHandler;

public class ChessboardGUI 
{
  @SuppressWarnings("unused")
  ChessGameHandler game = new ChessGameHandler();
 	JFrame chessboardWindow;
		JButton 

  public ChessboardGUI () 
		{
    chessboardWindow = new JFrame();
    chessboardWindow.setVisible(true);
  	}
}