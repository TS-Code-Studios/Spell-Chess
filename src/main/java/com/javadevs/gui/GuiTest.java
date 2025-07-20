package com.javadevs.gui;

import com.javadevs.gui.main.MainMenuGUI;


public class GuiTest {

    public static void main(String[] args) {
        new GuiTest().init_test();
    }

    public void init_test()
    {
        // Why is this here
        // ChessboardGUI MAIN_WINDOW = new ChessboardGUI();
        // MAIN_WINDOW.init_main_window();
        MainMenuGUI MAIN_WINDOW = new MainMenuGUI();
        System.out.println(MAIN_WINDOW.FRAME_HEIGHT);
    }

}
