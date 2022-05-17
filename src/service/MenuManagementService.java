package service;

import model.MenuItem;
import view.DisplayMenu;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static model.Menu.*;
import static utils.InputValidator.*;

public final class MenuManagementService implements IMenuManagementService {

    @Override
    public void showMenu() {
        new DisplayMenu("Currently in menu").display(); //prepare terminal menu screen
    }

    @Override
    public void removeMenuItem() {
        showMenu();
        getCurrentMenu().remove(getInt("Enter item to be removed"));
    }

    @Override
    public void addMenuItem() {
        getCurrentMenu().add(new MenuItem(getString("Enter menu item name"), getString("Enter menu item description"), getBigDecimal("Enter menu item price")));
        System.out.println("Menu item added");
    }

    @Override
    public void makeMenuItemAvailable() {
        showMenu();
        MenuItem item = getCurrentMenu().get(getInt("Enter item to be made available"));
        if (item.isAvailable())
            System.out.println("Item is already available");
        else {
            item.setAvailable(true);
            System.out.println("Item set as available");
        }
    }

    @Override
    public void exportMenu() {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./output/" + getString("Enter file name") + ".csv");
// CSV column names
            fileOutputStream.write("Product".getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(59); // ';'  separator
            fileOutputStream.write("Description".getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(59);
            fileOutputStream.write("Price".getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(System.getProperty("line.separator").getBytes(StandardCharsets.UTF_8));
            // contents
            for (MenuItem menuItem : getCurrentMenu()) {
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
    //TODO ^ refactor these two

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
        MenuItem menuItem = getCurrentMenu().get(getInt("Enter item to be set as not available"));
        if (!menuItem.isAvailable())
            System.out.println("Item is already not available");
        else {
            menuItem.setAvailable(false);
            System.out.println("Item set as not available");
        }
    }
}