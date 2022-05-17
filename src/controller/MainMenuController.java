package controller;


import java.util.Scanner;

import static controller.EmployeeManagementController.getEmployeeManagementController;
import static controller.OrdersController.getOrdersController;
import static controller.MenuManagementController.getMenuManagementController;
import static controller.RestaurantManagementController.getRestaurantManagementController;
import static main.Kitchen.setWorking;
import static view.EmployeeManagement.getEmployeeManagement;
import static view.InputValidator.getInt;
import static view.MainMenu.getMainMenu;
import static view.MenuManagement.getMenuManagement;
import static view.OrdersManagement.getOrdersManagement;
import static view.RestaurantManagement.getRestaurantManagement;

public final class MainMenuController implements TerminalEntryController {
    private static MainMenuController mainMenuController;

    public static MainMenuController getMainMenuController() {
        if (mainMenuController == null) {
            mainMenuController = new MainMenuController();
        }
        return mainMenuController;
    }

    public void invokeAction() {
        getMainMenu().display();
        switch (getInt("")) {

            case 0 -> getOrdersController().invokeAction();
            case 1 -> getRestaurantManagementController().invokeAction();
            case 2 -> getMenuManagementController().invokeAction();
            case 3 -> getEmployeeManagementController().invokeAction();
            default -> {
                System.out.println("Please choose and option from 0 to " + (getMainMenu().getActions().size() - 1));
                invokeAction();
            }
        }
        System.out.println("\n");
        invokeAction();
    }
}
