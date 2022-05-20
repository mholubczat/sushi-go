package model;

import java.util.ArrayList;

public class Menu {
    private static ArrayList<MenuItem> currentMenu;

    public static ArrayList<MenuItem> getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(ArrayList<MenuItem> currentMenu) {
        Menu.currentMenu = currentMenu;
    }
}