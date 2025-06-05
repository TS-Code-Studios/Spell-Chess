package com.javadevs.gui.game;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class BoardSpace extends JPanel
{
    JPanel LEFT_SPACER;
    JPanel RIGHT_SPACER;
    
    Dimension SPACER_SIZE;

    BoxLayout SPACE_LAYOUT;

    public BoardSpace(ChessBoard board, ChessboardGUI frame)
    {
        int HORIZONTAL_SPACING = (frame.FRAME_SIZE.width - board.BOARD_SIZE.width) / 2;
        SPACER_SIZE = new Dimension(HORIZONTAL_SPACING, board.BOARD_SIZE.height);
        System.out.println("SPACER_SIZE: " + SPACER_SIZE);
        SPACE_LAYOUT = new BoxLayout(this, BoxLayout.X_AXIS);

        setLayout(SPACE_LAYOUT);

        Dimension SPACE_SIZE_BUFFER = new Dimension(frame.FRAME_SIZE.width, board.BOARD_SIZE.height);
        setPreferredSize(SPACE_SIZE_BUFFER);

        setOpaque(false);
        setVisible(true);

        build_spacers();
        add_spacers(board);
    }

    public void build_spacers()
    {
        LEFT_SPACER = new JPanel();
        RIGHT_SPACER = new JPanel();

        LEFT_SPACER.setPreferredSize(SPACER_SIZE);
        RIGHT_SPACER.setPreferredSize(SPACER_SIZE);

        LEFT_SPACER.setOpaque(false);
        RIGHT_SPACER.setOpaque(false);

        LEFT_SPACER.setVisible(true);
        RIGHT_SPACER.setVisible(true);
    }

    public void add_spacers(ChessBoard board)
    {
        add(LEFT_SPACER);
        add(board);
        add(RIGHT_SPACER);
        System.out.println("ActualSize: " + LEFT_SPACER.getSize());
    }

}

