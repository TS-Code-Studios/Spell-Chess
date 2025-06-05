package com.javadevs.gui.game;

import com.javadevs.ChessGameHandler;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ChessBoard extends JPanel
{
    List<chessButton> BUTTON_LIST;
    int BUTTON_COUNT;
    int CURRENT_ROW;
    int BUTTON_SIZE;

    public Dimension BOARD_SIZE;

    int BOARD_WIDTH;

    public ChessBoard(ChessGameHandler arrayHandler) 
    {
        BUTTON_LIST = new ArrayList<>();
        
        BUTTON_COUNT = 0;
        CURRENT_ROW = 1; // Reset countRow for the first column
        
        BOARD_SIZE = new Dimension(320, 320); // Set the size of the board
        BOARD_WIDTH = BOARD_SIZE.width; // Store the true width of the board
        
        setBackground(Color.LIGHT_GRAY);
        setMinimumSize(BOARD_SIZE);
        setMaximumSize(BOARD_SIZE);
        setPreferredSize(BOARD_SIZE);
        setVisible(true);
        setOpaque(false); // Make sure the panel is opaque
        setFocusable(true); // Make the panel focusable
        setLayout(new GridLayout(8, 8));

        BUTTON_SIZE = BOARD_WIDTH / 8; // Since it's an 8x8 grid, divide the panel size by 8
        boardInit(arrayHandler);

        //set_debug_board_labels(arrayHandler); // Set debug labels for the buttons
    }
    
    public void boardInit(ChessGameHandler arrayHandler)
 
    {
      BUTTON_COUNT = 0;
      column_init("a");
      column_init("b");
      column_init("c");
      column_init("d");
      column_init("e");
      column_init("f");
      column_init("g");
      column_init("h");
      set_test_icons(arrayHandler);
    }

  public void column_init(String column)
    {
      for(int i=0; i<8; i++)
    {
      chessButton button = new chessButton( column, String.valueOf(CURRENT_ROW));
      button_config(button);
      BUTTON_LIST.add(button);
      this.add(button);
      CURRENT_ROW++;
      BUTTON_COUNT++;
    }
    CURRENT_ROW = 1;
  }

  
  public void button_config(chessButton target)
  {
    target.setPreferredSize(new Dimension(BUTTON_COUNT, BUTTON_SIZE));
    ActionListener ON_BUTTON_CLICK = new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println(target.posY + target.posX);
                System.out.println(target.getSize()); // Print the position of the button
            }
        };
    target.addActionListener(ON_BUTTON_CLICK);
    target.setSquareColor();
    target.setFocusPainted(false);
    target.setBorderPainted(false);    
  }

  public void set_debug_button_labels(ChessGameHandler arrayHandler, chessButton button)
  {
    String piece = arrayHandler.position[button.intX][button.intY];
    
    button.setText(piece); // Set the button label to the piece name
  
  }

  public void set_global_debug_labels(ChessGameHandler arrayHandler) // WRAPPER FOR SETTING DEBUG LABELS
  {
    for (chessButton button : BUTTON_LIST) 
    {
      set_debug_button_labels(arrayHandler, button);
    }
  }

  public void set_test_icons(ChessGameHandler arrayHandler)
  {
    try {
        // Load the image only once


        for (chessButton button : BUTTON_LIST) 
        {
            String PIECE_BUFFER = button.getPiece(arrayHandler); // Get the piece from the game handler's position array
            if(PIECE_BUFFER != "-")
            {
            BufferedImage img = ImageIO.read(getClass().getResource("/" + PIECE_BUFFER + ".png"));
            Image scaledImg = img.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImg);
            button.setIcon(icon);
            button.setText(""); // Optionally clear text
            }
          }
    } catch (Exception e) {
        e.printStackTrace();
    }
  }

}