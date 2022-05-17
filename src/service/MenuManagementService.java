package service;


import model.MenuItem;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static model.Menu.getMenu;
import static model.Menu.setCurrentMenu;
import static view.InputValidator.*;
import static view.RestaurantMenu.getRestaurantMenu;

public final class MenuManagementService implements IMenuManagementService {
    private static MenuManagementService menuManagementService;


    public static MenuManagementService getMenuManagementService() {
        if (menuManagementService == null) {
            menuManagementService = new MenuManagementService();
        }
        return menuManagementService;
    }

    @Override
    public void showMenu() {
        getRestaurantMenu().display();
    }

    @Override
    public void removeMenuItem() {
        showMenu();
        getMenu().removeMenuItem(getInt("Enter item to be removed"));
    }

    @Override
    public void addMenuItem() {
        getMenu().addMenuItem(new MenuItem(getString("Enter menu item name"), getString("Enter menu item description"), getBigDecimal("Enter menu item price")));
        System.out.println("Menu item added");
    }

    @Override
    public void makeMenuItemAvailable() {
        showMenu();
        MenuItem item = getMenu().getCurrentMenu().get(getInt("Enter item to be made available"));
        if (item.isAvailable())
            System.out.println("Item is already available");
        else {
            item.setAvailable(true);
            System.out.println("Item set as available");
        }
    }

    @Override
    public void exportMenu() {
        System.getProperty("line.separator");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./output/" + getString("Enter file name") + ".csv");

            fileOutputStream.write("Product".getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(59);
            fileOutputStream.write("Description".getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(59);
            fileOutputStream.write("Price".getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(System.getProperty("line.separator").getBytes(StandardCharsets.UTF_8));


            for (MenuItem menuItem : getMenu().getCurrentMenu()) {
                fileOutputStream.write(menuItem.getName().getBytes(StandardCharsets.UTF_8));
                fileOutputStream.write(59);
                fileOutputStream.write(menuItem.getDescription().getBytes(StandardCharsets.UTF_8));
                fileOutputStream.write(59);
                fileOutputStream.write(menuItem.getPrice().toPlainString().getBytes(StandardCharsets.UTF_8));
                fileOutputStream.write(System.getProperty("line.separator").getBytes(StandardCharsets.UTF_8));

            }
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void importMenu() {
        try {
            FileReader fr = new FileReader("./input/" + getString("Enter file name") + ".csv");
            readCsv(fr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void importMenu(String fileName) {
        try {
            FileReader fr = new FileReader("./input/" + fileName + ".csv");
            readCsv(fr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readCsv(FileReader fr) throws IOException {
        BufferedReader br = new BufferedReader(fr);
        List<String> data = br.lines().skip(1).flatMap(l -> Arrays.stream(l.split(";"))).toList();
        ArrayList<MenuItem> menu = new ArrayList<>();
        for (int i = 0; i < data.size(); i += 3) {
            menu.add(new MenuItem(data.get(i), data.get(i + 1), BigDecimal.valueOf(Double.parseDouble(data.get(i + 2)))));
        }
        setCurrentMenu(menu);
        fr.close();
    }

    @Override
    public void makeMenuItemNotAvailable() {
        showMenu();
        MenuItem menuItem = getMenu().getCurrentMenu().get(getInt("Enter item to be set as not available"));
        if (!menuItem.isAvailable())
            System.out.println("Item is already not available");
        else {
            menuItem.setAvailable(false);
            System.out.println("Item set as not available");
        }
    }
}