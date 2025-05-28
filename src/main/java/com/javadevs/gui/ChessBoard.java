package com.javadevs.gui;

//Developer: simonkdev
//Version: 0.1 10.03.2025
//add Information requests here:

import com.javadevs.gui.chessButton;
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

    public ChessBoard()
    {
        buttons = new ArrayList<>();
        count = 0;
        countRow = 1; // Reset countRow for the first column
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setPreferredSize(new Dimension(400, 400)); // Set preferred size for the panel
        setVisible(true);
        setOpaque(true); // Make sure the panel is opaque
        setFocusable(true); // Make the panel focusable
        setLayout(new GridLayout(8, 8));
        int panelWidth = 400;  // Width of the panel
        buttonSize = panelWidth / 8; // Since it's an 8x8 grid, divide the panel size by 8
        boardInit();
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
    target.setPreferredSize(new Dimension(buttonSize, buttonSize));
    ActionListener ON_BUTTON_CLICK = new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println(target.posY + target.posX); // Print the position of the button
            }
        };
    target.addActionListener(ON_BUTTON_CLICK);
    target.setSquareColor();
    //target.setLabel(target.posY + target.posX);
    target.setText(target.posY + target.posX);
    
  }
    
}