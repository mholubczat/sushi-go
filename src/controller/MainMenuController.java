package controller;

import service.IRestaurantManagementService;

import static utils.InputValidator.getInt;
import static view.MainMenu.getMainMenu;

public final class MainMenuController {
private final EmployeeManagementController employeeManagementController;
private final MenuManagementController menuManagementController;
private final RestaurantManagementController restaurantManagementController;
private final OrdersController ordersController;

    public MainMenuController(EmployeeManagementController employeeManagementController, MenuManagementController menuManagementController, RestaurantManagementController restaurantManagementController, OrdersController ordersController) {
        this.employeeManagementController = employeeManagementController;
        this.menuManagementController = menuManagementController;
        this.restaurantManagementController = restaurantManagementController;
        this.ordersController = ordersController;
    }

    public void invokeAction() {
        getMainMenu().display();
        switch (getInt("")) {
            case 0 -> ordersController.invokeAction();
            case 1 -> restaurantManagementController.invokeAction();
            case 2 -> menuManagementController.invokeAction();
            case 3 -> employeeManagementController.invokeAction();
            default -> {
                System.out.println("Please choose and option from 0 to " + (getMainMenu().getActions().size() - 1));
                invokeAction();
            }
        }
        System.out.println("\n");
        invokeAction();
    }
}
