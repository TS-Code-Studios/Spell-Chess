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

    public BoardSpace(ChessBoard board, ChessboardGUI frame)
    {
        int WIDTH_SPACING = (frame.FRAME_SIZE.width - board.TRUE_BOARD_WIDTH) / 2;

<<<<<<< HEAD
<<<<<<< HEAD
        SPACER_SIZE = new Dimension(WIDTH_SPACING, board.boardSize.height);
        System.out.println("SPACER_SIZE: " + SPACER_SIZE);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setSize(400, 320);             //(new Dimension(frame.FRAME_SIZE.width, board.getHeight()));
        System.out.println("BoardSpace size: " + getSize());
=======
        SPACER_SIZE = new Dimension(WIDTH_SPACING, board.getHeight());

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setSize(new Dimension(frame.getWidth(), board.getHeight()));
>>>>>>> parent of b80bc00 (something broke idfk)
=======
        SPACER_SIZE = new Dimension(WIDTH_SPACING, board.getHeight());

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setSize(new Dimension(frame.getWidth(), board.getHeight()));
>>>>>>> parent of b80bc00 (something broke idfk)
        setOpaque(false);
        setVisible(true);
        build_spacers();
        add_spacers(board);
    }

    public void build_spacers()
    {
        LEFT_SPACER = new JPanel();
        RIGHT_SPACER = new JPanel();

        LEFT_SPACER.setSize(40, 320);
        RIGHT_SPACER.setSize(40, 320);

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
    }

}

