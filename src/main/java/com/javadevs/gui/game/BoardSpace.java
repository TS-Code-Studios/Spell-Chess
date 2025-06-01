package com.javadevs.gui.game;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class BoardSpace extends JPanel
{
    JPanel LEFT_SPACER;
    JPanel RIGHT_SPACER;
    
    Dimension SPACER_SIZE;

    GridLayout SPACED_LAYOUT;

    int WIDTH_SPACING;

    public BoardSpace(ChessBoard board, ChessboardGUI frame)
    {
        WIDTH_SPACING = (frame.FRAME_SIZE.width - board.TRUE_BOARD_WIDTH) / 2;
        System.out.println("WIDTH_SPACING: " + WIDTH_SPACING);


        SPACER_SIZE = new Dimension(WIDTH_SPACING, board.getHeight());
        System.out.println("SPACER_SIZE: " + SPACER_SIZE);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setSize(new Dimension(frame.FRAME_SIZE.width, board.getHeight()));
        System.out.println("BoardSpace size: " + getSize());
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

        if (WIDTH_SPACING <= 0) {
            LEFT_SPACER.setVisible(false);
            RIGHT_SPACER.setVisible(false);
        }


    }

    public void add_spacers(ChessBoard board)
    {
        add(LEFT_SPACER);
        add(board);
        add(RIGHT_SPACER);
    }

}

