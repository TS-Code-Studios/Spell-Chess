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

    ChessGameHandler GAME_HANDLER;

    int BOARD_WIDTH;

    chessButton LAST_SELECTION_BUFFER;

    public ChessBoard(ChessGameHandler arrayHandler) 
    {
        BUTTON_LIST = new ArrayList<>();
        
        BUTTON_COUNT = 0;
        CURRENT_ROW = 8 ; // Reset countRow for the first column
        
        BOARD_SIZE = new Dimension(320, 320); // Set the size of the board
        BOARD_WIDTH = BOARD_SIZE.width; // Store the true width of the board
        GAME_HANDLER = arrayHandler; // Store the game handler for later use

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
      row_init( 7);
      row_init( 6);
      row_init( 5);
      row_init( 4);
      row_init( 3);
      row_init( 2);
      row_init( 1);
      row_init( 0);
      set_test_icons(arrayHandler);
    }

  public void row_init(int rowIndex)
    {
      for(int i=0; i<8; i++)
    {
      chessButton button = new chessButton(get_latin_value(i), rowIndex);
      button_config(button);
      BUTTON_LIST.add(button);
      this.add(button);
      CURRENT_ROW--;
      BUTTON_COUNT++;
    }
    CURRENT_ROW = 8;
  }

  
  public void button_config(chessButton target)
  {
    target.setPreferredSize(new Dimension(BUTTON_COUNT, BUTTON_SIZE));
    ActionListener ON_BUTTON_CLICK = new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println(target.posX + (target.posY + 1));
                if (target.isPossibleMoveTarget)
                {
                    System.out.println("Possible move target clicked: " + target.name);
                    if (LAST_SELECTION_BUFFER != null) 
                    {
                        make_move(LAST_SELECTION_BUFFER, target, GAME_HANDLER); // Make the move if valid
                    }
                } 
                else 
                {
                    System.out.println("Button clicked: " + target.name);
                    set_button_selected(target, true); // Highlight the selected button
                }
                remove_target_highlights();
                show_possible_targets(target, GAME_HANDLER);
            }
        };
    target.addActionListener(ON_BUTTON_CLICK);
    target.convertPosition(); // Convert the position to numeric values for array access
    target.setSquareColor();
    target.setFocusPainted(false);
    target.setBorderPainted(false);    
    System.out.println("[DEBUGG] BUTTON NAME: " + target.name);
  }


  public void set_test_icons(ChessGameHandler arrayHandler)
  {
    try {
        for (chessButton button : BUTTON_LIST) 
        {
            char PIECE_BUFFER = button.getPiece(arrayHandler); // Get the piece from the game handler's position array
            if (PIECE_BUFFER != '-') {
                BufferedImage img = ImageIO.read(getClass().getResource("/" + PIECE_BUFFER + ".png"));
                Image scaledImg = img.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImg);
                button.setIcon(icon);
                button.setText(""); // Optionally clear text
            } else {
                button.setIcon(null); // <-- Clear icon if no piece
                button.setText("");   // Optionally clear text
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
  }

  public String get_latin_value(int column)
  {
    switch (column) 
    {
      case 0: return "a";
      case 1: return "b";
      case 2: return "c";
      case 3: return "d";
      case 4: return "e";
      case 5: return "f";
      case 6: return "g";
      case 7: return "h";
      default: return "";
    }
  }


  public void show_possible_targets(chessButton origin, ChessGameHandler arrayHandler)
  {
    set_button_selected(origin, true);
    origin.isPossibleMoveTarget = true; // Mark the origin button as a possible move target
    LAST_SELECTION_BUFFER = origin; // Store the last selected button for reference
    for (chessButton button : BUTTON_LIST) 
    {
        if (button != origin) // Avoid highlighting the origin button
        {
            String currentPieceString = Character.toString(origin.getPiece(arrayHandler)); // Get the piece at the current button
            // Check if the button is a valid target for the piece at the origin
            if (arrayHandler.isMovePossible(currentPieceString, origin.name, button.name, true)) // Replace "a1" and "a3" with actual positions
            {
                button.setText("⬤");
                button.isPossibleMoveTarget = true; // Mark the button as a possible move target
            } 
            else  
            {
                button.setBorder(BorderFactory.createEmptyBorder()); // Remove highlight for invalid targets
                button.isPossibleMoveTarget = false; // Mark the button as not a possible move target
            }
        }
    }
  }

  public void remove_target_highlights()
  {
    if (LAST_SELECTION_BUFFER != null) 
    {
        set_button_selected(LAST_SELECTION_BUFFER, false); // Deselect the last selected button
    }
    for (chessButton button : BUTTON_LIST) 
    {
        button.setText(""); // Clear the text to remove highlights
        button.setBorder(BorderFactory.createEmptyBorder()); // Remove any border highlights
    }
  }

  public void set_button_selected(chessButton target, boolean selected) 
  {
    target.isSelected = selected; // Set the selection state of the button
    if (selected) 
    {
        target.setBackground(Color.YELLOW); // Change background color to indicate selection
    } 
    else 
    {
        target.setSquareColor(); // Reset to original color
    }
  }

  public void make_move(chessButton origin, chessButton target, ChessGameHandler arrayHandler) 
  {
    String currentPieceString = Character.toString(origin.getPiece(arrayHandler)); // Get the piece at the origin button
    if( arrayHandler.isMovePossible(currentPieceString, origin.name, target.name, true)) 
    {
      arrayHandler.makeMove(currentPieceString, origin.name, target.name, false, 'a'); // Perform the move
      arrayHandler.switchPlayerToMove();
      // set_test_icons(arrayHandler);
    }
    else 
    {
      System.out.println("Invalid move from " + origin.name + " to " + target.name);
      // set_test_icons(arrayHandler);
    }
    set_test_icons(arrayHandler); // Reload icons after the move
  }

}