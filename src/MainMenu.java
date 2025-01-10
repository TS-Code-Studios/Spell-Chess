import javax.swing.*;

public class MainMenu {
    //Every element of the GUI is defined
    JFrame mainMenu;
    JPanel title;
    /* Method for creating the main menu JFrame, making it visible,
    creating the elements and adding the elements to the window. */
    public static void launchMainMenu() {
        mainMenu = new JFrame();
        title = new JPanel("Spell Chess");
        
        mainMenu.add(title);
        mainMenu.setSize(500, 500);
        mainMenu.setLocationRelativeTo(null);
        mainMenu.pack();
        mainMenu.setVisible(true);
    }
}
