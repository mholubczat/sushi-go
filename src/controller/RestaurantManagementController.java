package controller;


import service.IMenuManagementService;
import service.IRestaurantManagementService;

import static service.MenuManagementService.getMenuManagementService;
import static service.RestaurantManagementService.getRestaurantManagementService;
import static view.InputValidator.getInt;
import static view.MenuManagement.getMenuManagement;
import static view.OrdersManagement.getOrdersManagement;
import static view.RestaurantManagement.getRestaurantManagement;

public final class RestaurantManagementController implements TerminalEntryController {
    private static RestaurantManagementController restaurantManagementController;
    private final IRestaurantManagementService restaurantManagementService;

    public static RestaurantManagementController getRestaurantManagementController() {
        if (restaurantManagementController == null) {
            restaurantManagementController = new RestaurantManagementController(getRestaurantManagementService());
        }
        return restaurantManagementController;
    }

    public RestaurantManagementController(IRestaurantManagementService restaurantManagementService) {
        this.restaurantManagementService = restaurantManagementService;
    }

    public void invokeAction() {
        getRestaurantManagement().display();
        switch (getInt("")) {

            case 0 -> restaurantManagementService.startRestaurantWork();
            case 1 -> restaurantManagementService.stopRestaurantWork();
            case 2 -> restaurantManagementService.displayTurnover();
            case 3 -> {
            }
            default -> {
                System.out.println("Please choose and option from 0 to " + (getRestaurantManagement().getActions().size() - 1));
                invokeAction();
            }
        }
    }
}
