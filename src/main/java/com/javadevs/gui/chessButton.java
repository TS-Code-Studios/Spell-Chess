package com.javadevs;

//Author: @simonkdev
//Version: 17/2/2025
//ask for more information if needed

import java.util.Scanner;
import com.javadevs.ChessGameHandler;

public class ChessButton
{
			String name;
			JButton baseButton;
			String buttonPos;
					
			public ChessButton(String positionButton)
			{
					name = "button" + positionButton;
					baseButton = new JButton;
					buttonPos = positionButton;
			}
}