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
            restaurantMenu.addAction("POSITION NOT AVAILABLE - " + (menuItem.getName() + "\t\t\t" + menuItem.getDescription() + "\t\t\t" + menuItem.getPrice() + "zł"));
            else restaurantMenu.addAction((menuItem.getName() + "\t\t\t" + menuItem.getDescription() + "\t\t\t" + menuItem.getPrice() + "zł"));
        }
        //https://stackoverflow.com/questions/15215326/how-can-i-create-table-using-ascii-in-a-console
        return restaurantMenu;
    }

}
