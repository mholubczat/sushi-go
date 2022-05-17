package view;

import model.MenuItem;

import java.util.ArrayList;

import static model.Menu.getMenu;

public final class RestaurantMenu extends TerminalEntry {

    private static RestaurantMenu restaurantMenu;

    public RestaurantMenu(String message) {
        super(message);
    }

    public static RestaurantMenu getRestaurantMenu() {
        if (restaurantMenu == null) {
            restaurantMenu = new RestaurantMenu("Currently in menu:");
        }
        restaurantMenu.setActions(new ArrayList<>());
        for (MenuItem menuItem : getMenu().getCurrentMenu()) {
            if(!menuItem.isAvailable())
            restaurantMenu.addAction("POSITION NOT AVAILABLE - " + (menuItem.getName() + "\t" + menuItem.getDescription() + "\t" + menuItem.getPrice() + "zł"));
            else restaurantMenu.addAction((menuItem.getName() + "\t" + menuItem.getDescription() + "\t" + menuItem.getPrice() + "zł"));
        }
        return restaurantMenu;
    }

}
