package com.javadevs.gui;

import com.javadevs.gui.ChessboardGUI;


public class guiTest {

    public static void main(String[] args) {
        new guiTest().init_test();
    }

    public void init_test()
    {
        ChessboardGUI MAIN_WINDOW = new ChessboardGUI();
        MAIN_WINDOW.init_main_window();
    }

}
