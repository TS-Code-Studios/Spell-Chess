package com.javadevs.gui.main;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class NGPanel extends JPanel
{
    Dimension PANEL_SIZE;
    NGButton NG_BUTTON;

    BoxLayout PANEL_LAYOUT;

    JPanel LEFT_SPACER;
    JPanel RIGHT_SPACER;

    public NGPanel(MainMenuGUI mainFrame)
    {
        PANEL_LAYOUT = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(PANEL_LAYOUT);

        NG_BUTTON = new NGButton(mainFrame);
        PANEL_SIZE = new Dimension(mainFrame.FRAME_WIDTH, NG_BUTTON.getHeight());
        setSize(PANEL_SIZE);
        setPreferredSize(PANEL_SIZE);
        setOpaque(false);

        init_spacers(mainFrame, NG_BUTTON);

        System.out.println("Initializing NGPanel with size: " + PANEL_SIZE);
        System.out.println("Current left Spacer size: " + LEFT_SPACER.getSize());
        System.out.println("Current NG Button size: " + NG_BUTTON.getSize());

        add(LEFT_SPACER);
        add(NG_BUTTON);
        add(RIGHT_SPACER);
        System.out.println("Final Button Height: " + NG_BUTTON.getHeight());

        setVisible(true);
    }

    public void init_spacers(MainMenuGUI mainFrame, NGButton button)
    {
        Dimension spacerSize = new Dimension(((mainFrame.FRAME_WIDTH - button.getWidth()) / 2), button.getHeight() );
        
        LEFT_SPACER = new JPanel();
        RIGHT_SPACER = new JPanel();

        LEFT_SPACER.setSize(spacerSize);
        RIGHT_SPACER.setSize(spacerSize);
        LEFT_SPACER.setPreferredSize(spacerSize);
        RIGHT_SPACER.setPreferredSize(spacerSize);
        

        LEFT_SPACER.setOpaque(false);
        LEFT_SPACER.setVisible(true);

        RIGHT_SPACER.setOpaque(false);
        RIGHT_SPACER.setVisible(true);
    }


}
