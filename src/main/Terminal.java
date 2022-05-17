package main;

import controller.*;
import model.Kitchen;
import service.*;

public class Terminal implements Runnable {
    @Override
    public void run() {
        new MainMenuController(
                new EmployeeManagementController(new EmployeeManagementService()),
                new MenuManagementController(new MenuManagementService()),
                new RestaurantManagementController(new RestaurantManagementService(new Kitchen())),
                new OrdersController(new OrdersService())
        ).invokeAction();
    }
}


