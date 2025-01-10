import javax.swing.*;

public class MainMenuGUI {
    //Every element of the GUI is defined
    JFrame mainMenuFrame;
    JPanel titlePanel;
    /* Method for creating the main menu JFrame, making it visible,
    creating the elements and adding the elements to the window. */
    public static void launchMainMenu() {
        mainMenuFrame = new JFrame();
        titlePanel = new JPanel("Spell Chess");
        
        mainMenuFrame.add(titlePanel);
        mainMenuFrame.setSize(500, 500);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.pack();
        mainMenuFrame.setVisible(true);
    }
}
