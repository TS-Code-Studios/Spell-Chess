import javax.swing.*;

public class Main extends JFrame {

    public static void createAndShowGUI() {
        JFrame testGUI = new JFrame();
        JButton testButton = new JButton("test");    
        testGUI.add(testButton);
        testGUI.setSize(500, 500);
        testGUI.setLocationRelativeTo(null);
        testGUI.pack();
        testGUI.setVisible(true);
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}