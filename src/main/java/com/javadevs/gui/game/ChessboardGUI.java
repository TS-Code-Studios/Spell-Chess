package com.javadevs.gui.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

//Developer: @simonkdev
//Version: 17/02/2025
//For more information check out INFORMATION.md



import javax.swing.Box;
import javax.swing.JFrame;


import com.javadevs.ChessGameHandler;



//für position[][] gilt: position[y][x] (im Array)

public class ChessboardGUI extends JFrame
{
  public Dimension FRAME_SIZE = new Dimension(400, 600);

  ChessGameHandler gameHandler;
  ChessBoard board;
  bottomBar bottomBar;
  topBar topBar;
  BoardSpace spacedBoard;

  Dimension TOP_SPACER_SIZE;
  Dimension BOTTOM_SPACER_SIZE;

  Color BACKGROUND_COLOR_RGB = new Color(89, 84, 87); // RGB for black background

  // Remove BoxLayout, use GridBagLayout instead
  // BoxLayout WINDOW_LAYOUT = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);

  public ChessboardGUI () 
  {
     gameHandler = new ChessGameHandler();
     board = new ChessBoard(gameHandler);
     bottomBar = new bottomBar(this);
     topBar = new topBar(this);
     spacedBoard = new BoardSpace(board, this);
  }

  public void init_main_window()
  {
    window_init();
    init_spacer_sizes();

    getContentPane().setBackground(BACKGROUND_COLOR_RGB); // Set background color

    setLayout(new GridBagLayout());

    int row = 0;
    addWithConstraints(topBar, row++, topBar.BAR_HEIGHT, false);
    addWithConstraints(Box.createRigidArea(TOP_SPACER_SIZE), row++, TOP_SPACER_SIZE.height, true);
    addWithConstraints(spacedBoard, row++, board.BOARD_SIZE.height, false);
    addWithConstraints(Box.createRigidArea(BOTTOM_SPACER_SIZE), row++, BOTTOM_SPACER_SIZE.height, true);
    addWithConstraints(bottomBar, row, bottomBar.BAR_HEIGHT, false);

    setVisible(true); // <-- Move here!
  }

  private void addWithConstraints(java.awt.Component comp, int gridy, int height, boolean isSpacer) {
    GridBagConstraints gbc = configureConstraints(gridy, isSpacer);
    comp.setPreferredSize(new Dimension(FRAME_SIZE.width, height));
    getContentPane().add(comp, gbc);
  }

  private GridBagConstraints configureConstraints(int gridy, boolean isSpacer) {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = gridy;
    gbc.fill = isSpacer ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    gbc.weighty = isSpacer ? 1.0 : 0.0;
    gbc.ipady = 0;
    return gbc;
  }

  public void init_spacer_sizes()
  {
    double VERTICAL_SPACING_BUFFER = 0.5 * (FRAME_SIZE.height - (board.BOARD_SIZE.height + topBar.BAR_HEIGHT + bottomBar.BAR_HEIGHT));
    System.out.println("Vertical spacing buffer: " + VERTICAL_SPACING_BUFFER);
    int VERTICAL_SPACING = (int) VERTICAL_SPACING_BUFFER;
    System.out.println("Vertical spacing: " + VERTICAL_SPACING);

    BOTTOM_SPACER_SIZE = new Dimension(FRAME_SIZE.width, VERTICAL_SPACING);
    TOP_SPACER_SIZE = new Dimension(FRAME_SIZE.width, VERTICAL_SPACING);

  }

  public void window_init()
  {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Spell-Chess");
    setSize(FRAME_SIZE);
    // setLayout(WINDOW_LAYOUT); // Remove this line, layout is set in init_main_window
    setResizable(false);
    // setVisible(true); // <-- Remove from here!
  }
}

