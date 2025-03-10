package com.javadevs.gui;

//Author: @simonkdev
//Version: 17/2/2025
//ask for more information if needed

import javax.swing.JButton;

public class chessButton 
extends JButton
{
    @SuppressWarnings("unused")
    String name;
    static String posY;
    static String posX;
    static String color;
    static int intX;
    static int intY;
    
    public chessButton(String posXNew, String posYNew)
    {
        name = "button" + posXNew + posYNew;
        posY = posXNew;
        posX = posYNew;
        //2D array position[][] from ChessGameHandler.java has format position[y][x]
    }
    
    public static void convertPosition(String pos) 
    {
        intX = pos.charAt(0) - 'a'; 								// Convert column letter to number (0-based), meaning a=0, b=1, c=2, ...
        intY = Character.getNumericValue(pos.charAt(1)) - 1; 		// Convert row number to 0-based index, meaning 1=0, 2=1, 3=2, ...
    }

    public static void setSquareColor() 
    {
        convertPosition(posY + posX);                                   // Convert the position to 0-based coordinates
        
        int sum = intX + intY;                                          // Calculate the sum of the coordinates
        
        // Determine the color based on the sum and set the global variable
        if (sum % 2 == 0) 
        {
            color = "black";
        } 
        else 
        {
            color = "white";
        }
    }
}

