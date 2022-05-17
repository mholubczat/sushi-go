package main;

import static controller.MainMenuController.getMainMenuController;
import static view.MainMenu.getMainMenu;

public class Terminal extends Thread {


    @Override
    public void run() {
        super.run();
        getMainMenuController().invokeAction();
    }
}