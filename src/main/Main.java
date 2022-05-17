package main;

import model.MenuItem;
import service.MenuManagementService;
import service.OrdersService;
import service.RestaurantManagementService;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

import static bootstrap.DataInitializer.initialize;
import static model.Menu.getMenu;
import static view.InputValidator.getInt;
import static view.InputValidator.getPhone;

public class Main {
    public static void main(String[] args) {
        initialize();
        new Terminal().start();
        new Kitchen().start();
    }
}
