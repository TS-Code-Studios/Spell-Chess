package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenuGUI {
    /* Method for creating the main menu JFrame, making it visible,
    creating the elements and adding the elements to the window. */
    public static void launchMainMenu() {
        //graphics and device could later be used to implement a fullscreen button
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //Since the variable device isn't read, this suppresses the resulting error message
        @SuppressWarnings("unused")
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        //All elements of the main menu are defined
        JFrame mainMenuFrame = new JFrame();
        JLabel titleLabel = new JLabel("Spell Chess");
        
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(screenSize.width, screenSize.height);
        mainMenuFrame.setLocationRelativeTo(null);
        //The window is maximized as soon as it's opened
        mainMenuFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                mainMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
        
        //All elements are added to the main menu JFrame
        mainMenuFrame.add(titleLabel);
        mainMenuFrame.pack();
        
        //The JFrame is made visible
        mainMenuFrame.setVisible(true);
        
        /* device.setFullScreenWindow(mainMenuFrame); < This line can be uncommented and moved to a function
        that is run by a fullscreen button; it sets the window to be fullscreen */
    }
}
