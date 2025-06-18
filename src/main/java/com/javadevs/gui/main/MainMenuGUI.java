package com.javadevs.gui.main;

import java.awt.*;
import javax.swing.*;

import com.javadevs.gui.game.topBar;

public class MainMenuGUI extends JFrame
{
    public int FRAME_WIDTH = 400;
    public int FRAME_HEIGHT = 600;

    public NGPanel NG_PANEL;
    public mainBar TOP_BAR;

    public GridBagLayout FRAME_LAYOUT;

    Dimension TOP_SPACER_SIZE;
    Dimension BOTTOM_SPACER_SIZE;
    
    public MainMenuGUI()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Chess");
        setResizable(false);


        getContentPane().setBackground(new Color(89, 84, 87));
        
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        
        NG_PANEL = new NGPanel(this);
        TOP_BAR = new mainBar(this);
        init_spacer_sizes();
        
        
        FRAME_LAYOUT = new GridBagLayout();
        setLayout(FRAME_LAYOUT);
        int row = 0;
        addWithConstraints(TOP_BAR, row++, topBar.HEIGHT, false);
        addWithConstraints(Box.createRigidArea(TOP_SPACER_SIZE), row++, TOP_SPACER_SIZE.height, true);
        addWithConstraints(NG_PANEL, row++, NG_PANEL.getHeight(), false);
        addWithConstraints(Box.createRigidArea(BOTTOM_SPACER_SIZE), row++, BOTTOM_SPACER_SIZE.height, true);


        


        setVisible(true);
    }

    private void addWithConstraints(java.awt.Component comp, int gridy, int height, boolean isSpacer) {
    GridBagConstraints gbc = configureConstraints(gridy, isSpacer);
    // comp.setPreferredSize(new Dimension(FRAME_WIDTH, height));
    getContentPane().add(comp, gbc);
  }

  private GridBagConstraints configureConstraints(int gridy, boolean isSpacer) {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = gridy;
    gbc.fill = isSpacer ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    gbc.weighty = isSpacer ? 1.0 : 0.0;
    gbc.ipady = 0;
    return gbc;
  }

  public void init_spacer_sizes()
  {
    int bottomHeight = (FRAME_HEIGHT / 2) - NG_PANEL.getHeight();
    BOTTOM_SPACER_SIZE = new Dimension(FRAME_WIDTH, bottomHeight);

    int topHeight = (FRAME_HEIGHT / 2) - TOP_BAR.BAR_HEIGHT;
    TOP_SPACER_SIZE = new Dimension(FRAME_WIDTH, topHeight);

    System.out.println("Top Spacer: " + TOP_SPACER_SIZE);
    System.out.println("Bottom Spacer: " + BOTTOM_SPACER_SIZE);
  }

}

