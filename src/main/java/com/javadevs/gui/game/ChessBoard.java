package com.javadevs.gui.game;

import com.javadevs.ChessGameHandler;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ChessBoard extends JPanel
{
    List<chessButton> buttons;
    int count;
    int countRow;
    int buttonSize;
    public Dimension boardSize;
    int TRUE_BOARD_WIDTH;

    public ChessBoard(ChessGameHandler arrayHandler) 
    {
        buttons = new ArrayList<>();
        count = 0;
        countRow = 1; // Reset countRow for the first column
<<<<<<< HEAD
        boardSize = new Dimension(320, 320); // Set the size of the board
=======
        boardSize = new Dimension(300, 300); // Set the size of the board
>>>>>>> parent of b80bc00 (something broke idfk)
        TRUE_BOARD_WIDTH = boardSize.width; // Store the true width of the board
        
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setSize(new Dimension(boardSize)); // Set preferred size for the panel
        setVisible(true);
        setOpaque(true); // Make sure the panel is opaque
        setFocusable(true); // Make the panel focusable
        setLayout(new GridLayout(8, 8));
        int panelWidth = 300;  // Width of the panel
        buttonSize = panelWidth / 8; // Since it's an 8x8 grid, divide the panel size by 8
        boardInit();
        set_debug_board_labels(arrayHandler); // Set debug labels for the buttons
    }
    
    public void boardInit()
    {
      count = 0;
      columnInit("a");
      columnInit("b");
      columnInit("c");
      columnInit("d");
      columnInit("e");
      columnInit("f");
      columnInit("g");
      columnInit("h");
    }

  public void columnInit(String column)
    {
      for(int i=0; i<8; i++)
    {
      chessButton button = new chessButton( column, String.valueOf(countRow));
      buttonConfig(button);
      buttons.add(button);
      this.add(button);
      countRow++;
      count++;
    }
    countRow = 1;
  }

  @SuppressWarnings("deprecation")
  public void buttonConfig(chessButton target)
  {
    target.setSize(new Dimension(buttonSize, buttonSize));
    ActionListener ON_BUTTON_CLICK = new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println(target.posY + target.posX); // Print the position of the button
            }
        };
    target.addActionListener(ON_BUTTON_CLICK);
    target.setSquareColor();
<<<<<<< HEAD
    //target.setLabel(" ");
    //target.setIcon(TEST_BUTTON_ICON);
    target.setFocusPainted(false);
    target.setBorderPainted(false);    
=======
    //target.setLabel(target.posY + target.posX);
    target.setFocusPainted(false);    
>>>>>>> parent of b80bc00 (something broke idfk)
  }

  public void set_debug_button_labels(ChessGameHandler arrayHandler, chessButton button)
  {
    String piece = arrayHandler.position[button.intX][button.intY];
    
    button.setText(piece); // Set the button label to the piece name
  
  }

  public void set_debug_board_labels(ChessGameHandler arrayHandler)
  {
    for (chessButton button : buttons) 
    {
      set_debug_button_labels(arrayHandler, button);
    }
  }


}