package main;

import static controller.MainMenuController.getMainMenuController;

public class Terminal extends Thread {


    @Override
    public void run() {
        super.run();
        getMainMenuController().invokeAction();
    }
}