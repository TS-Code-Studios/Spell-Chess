package com.javadevs.gui;

//Developer: @simonkdev
//Version: 17/02/2025
//For more information check out INFORMATION.md

import javax.swing.JFrame;

import com.javadevs.ChessGameHandler;
import com.javadevs.ChessButton;

//für position[][] gilt: position[y][x] (im Array)

public class ChessboardGUI 
{
  @SuppressWarnings("unused")
  ChessGameHandler game = new ChessGameHandler();
 	 JFrame chessboardWindow;
	 

  public ChessboardGUI () 
		{
    chessboardWindow = new JFrame();
    chessboardWindow.setVisible(true);
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