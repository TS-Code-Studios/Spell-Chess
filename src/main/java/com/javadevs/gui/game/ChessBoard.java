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

    Icon TEST_BUTTON_ICON = new ImageIcon("src/main/resources/pieces/black-bishop.png"); // Path to the icon

    public ChessBoard(ChessGameHandler arrayHandler) 
    {
        buttons = new ArrayList<>();
        count = 0;
        countRow = 1; // Reset countRow for the first column
        boardSize = new Dimension(320, 320); // Set the size of the board
        TRUE_BOARD_WIDTH = boardSize.width; // Store the true width of the board
        
        System.out.println("TRUE_BOARD_WIDTH: " + TRUE_BOARD_WIDTH);
        System.out.println("Board size: " + boardSize);



        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setSize(new Dimension(boardSize)); // Set preferred size for the panel
        setVisible(true);
        setOpaque(false); // Make sure the panel is opaque
        setFocusable(true); // Make the panel focusable
        setLayout(new GridLayout(8, 8));
        int panelWidth = TRUE_BOARD_WIDTH;  // Width of the panel
        buttonSize = panelWidth / 8; // Since it's an 8x8 grid, divide the panel size by 8
        System.out.println("Button size: " + buttonSize);
        boardInit();
        //set_debug_board_labels(arrayHandler); // Set debug labels for the buttons
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
    System.out.println("Button size set to: " + target.getSize());
    ActionListener ON_BUTTON_CLICK = new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println(target.posY + target.posX);
                System.out.println("Button size: " + target.getSize());            }
        };
    target.addActionListener(ON_BUTTON_CLICK);
    target.setSquareColor();
    //target.setLabel(" ");
    //target.setIcon(TEST_BUTTON_ICON);
    target.setFocusPainted(false);
    target.setBorderPainted(false);    
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