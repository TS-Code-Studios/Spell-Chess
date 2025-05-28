package com.javadevs.gui.game;

import java.awt.Color;
import java.awt.Dimension;

//Developer: @simonkdev
//Version: 17/02/2025
//For more information check out INFORMATION.md

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import com.javadevs.ChessGameHandler;
import com.javadevs.gui.game.ChessBoard;
import com.javadevs.gui.game.bottomBar;

//für position[][] gilt: position[y][x] (im Array)

public class ChessboardGUI extends JFrame
{
  @SuppressWarnings("unused")
  
  public ChessboardGUI () 
  {
    //board.ChessBoard();

  }

  public void init_main_window()
  {
    ChessBoard board = new ChessBoard();
    ChessGameHandler gameHandler = new ChessGameHandler();
    bottomBar actionBar = new bottomBar();
    topBar topBar = new topBar();

    Dimension BOTTOM_SPACER = new Dimension(0, 50); // Space between the board and the action bar
    Dimension TOP_SPACER = new Dimension(0, 50); // Space at the top of the window

    setSize(400, 600);

    BoxLayout WINDOW_LAYOUT = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
    getContentPane().setLayout(WINDOW_LAYOUT);
  
    this.add(topBar);
    this.add(Box.createRigidArea(TOP_SPACER)); // Add some space at the top of the window
    this.add(board);
    this.add(Box.createRigidArea(BOTTOM_SPACER)); // Add some space between the board and the action bar
    this.add(actionBar);
    setTitle("Chessboard");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    setBackground(Color.BLACK);
    setResizable(false);
    setVisible(true);
  }
}

