package main;

import model.MenuItem;
import service.MenuManagementService;
import service.OrdersService;
import service.RestaurantManagementService;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

import static model.Menu.getMenu;
import static view.InputValidator.getInt;
import static view.InputValidator.getPhone;

public class Main {
    public static void main(String[] args) {

        getMenu().addMenuItem(new MenuItem("Hosomaki","Maki rolls 1pc.", BigDecimal.valueOf(1.21)));
        getMenu().addMenuItem(new MenuItem("Sashimi","Maki rolls 1pc.", BigDecimal.valueOf(7.21)));
        getMenu().addMenuItem(new MenuItem("Gyoza","Maki rolls 1pc.", BigDecimal.valueOf(3.21)));
        getMenu().addMenuItem(new MenuItem("Tempura","Maki rolls 1pc.", BigDecimal.valueOf(4.21)));
        getMenu().addMenuItem(new MenuItem("Ice cream","Maki rolls 1pc.", BigDecimal.valueOf(11.21)));
        getMenu().addMenuItem(new MenuItem("Cola","Maki rolls 1pc.", BigDecimal.valueOf(13.21)));

        new MenuManagementService().
                importMenu();

        new Terminal().start();
        new Kitchen().start();
    }
}
