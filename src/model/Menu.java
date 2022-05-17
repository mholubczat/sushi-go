package model;

import java.util.ArrayList;

public final class Menu {
    private static ArrayList<MenuItem> currentMenu;
    private static Menu menu;

    public static Menu getMenu() {
        if (menu == null) {
            menu = new Menu();
        }
        if(currentMenu == null) {
            currentMenu = new ArrayList<>();
        }
        return menu;

    }

    public ArrayList<MenuItem> getCurrentMenu() {
        return currentMenu;
    }

    public void addMenuItem(MenuItem menuItem){
        currentMenu.add(menuItem);
    }

    public void removeMenuItem(int number){
        currentMenu.remove(number);
    }

    public static void setCurrentMenu(ArrayList<MenuItem> currentMenu) {
        Menu.currentMenu = currentMenu;
    }
}