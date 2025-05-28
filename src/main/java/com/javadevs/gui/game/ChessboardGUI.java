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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

    Dimension BOTTOM_SPACER_SIZE = new Dimension(0, 50);
    Dimension TOP_SPACER_SIZE = new Dimension(0, 50);

    setSize(400, 600);

    BoxLayout WINDOW_LAYOUT = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
    getContentPane().setLayout(WINDOW_LAYOUT);

    // Set content pane background to black (or your image later)
    getContentPane().setBackground(Color.BLACK);

    // Use invisible, lightweight spacers
    getContentPane().add(topBar);
    getContentPane().add(Box.createRigidArea(TOP_SPACER_SIZE));
    getContentPane().add(board);
    getContentPane().add(Box.createRigidArea(BOTTOM_SPACER_SIZE));
    getContentPane().add(actionBar);

    setTitle("Chessboard");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  }
}

