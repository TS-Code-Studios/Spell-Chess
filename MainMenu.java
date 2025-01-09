import javax.swing.*;

public class MainMenu extends JFrame {
    public static void createAndShowGUI() {
        JFrame menuGUI = new JFrame();
        JPanel title = new JPanel("Spell Chess");    
        menuGUI.add(title);
        menuGUI.setSize(500, 500);
        menuGUI.setLocationRelativeTo(null);
        menuGUI.pack();
        menuGUI.setVisible(true);
    }
}
