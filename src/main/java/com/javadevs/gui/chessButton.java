package com.javadevs.gui;

//Author: @simonkdev
//Version: 17/2/2025
//ask for more information if needed
import com.javadevs.ChessGameHandler;

import java.awt.Color;

import javax.swing.JButton;

public class chessButton extends JButton
{
    @SuppressWarnings("unused")
    String name;
    String posY;
    String posX;
    static int intX;
    static int intY;
    public String color;
    
    public chessButton(String posXNew, String posYNew)
    {
        name = "button" + posXNew + posYNew;
        posY = posXNew;
        posX = posYNew;
        //2D array position[][] from ChessGameHandler.java has format position[y][x]
    }
    
    public static void convertPosition(String pos)                  //CONVERTS POSITION TO NUMERIC VALUE FOR LATER COLOR CALCULATION
    {
        intX = pos.charAt(0) - 'a'; 	        					// Convert column letter to number (0-based), meaning a=0, b=1, c=2, ...
        intY = Character.getNumericValue(pos.charAt(1)) - 1; 		// Convert row number to 0-based index, meaning 1=0, 2=1, 3=2, ...
    }



    public void setSquareColor() 
    {
        convertPosition(posY + posX);                                   // Convert the position to 0-based coordinates
        
        int sum = intX + intY;                                          // Calculate the sum of the coordinates
        
        // Determine the color based on the sum and set the global variable
        if (sum % 2 == 0) 
        {
            color = "black";
            this.setBackground(Color.BLACK); // Set the button background color to black
        } 
        else 
        {
            color = "white";
            this.setBackground(Color.WHITE); // Set the button background color to white
        }
    }

    public void loadSquareColor(String colorSet)
    {
        setBackground(Color.BLACK);
    }
}
