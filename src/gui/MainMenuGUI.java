package gui;

import java.awt.*;
import javax.swing.*;

public class MainMenuGUI {
    /* Method for creating the main menu JFrame, making it visible,
    creating the elements and adding the elements to the window. */
    public static void launchMainMenu() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        
        JFrame mainMenuFrame = new JFrame();
        JLabel titleLabel = new JLabel("Spell Chess");
        
        
        mainMenuFrame.add(titleLabel);
        device.setFullScreenWindow(mainMenuFrame);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.pack();
        mainMenuFrame.setVisible(true);
    }
}
