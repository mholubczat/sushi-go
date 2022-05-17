package controller;


import service.IRestaurantManagementService;

import static utils.InputValidator.getInt;
import static view.RestaurantManagement.getRestaurantManagement;

public final class RestaurantManagementController {
    private final IRestaurantManagementService restaurantManagementService;
    public RestaurantManagementController(IRestaurantManagementService restaurantManagementService) {
        this.restaurantManagementService = restaurantManagementService;
    }
    public void invokeAction() {
        getRestaurantManagement().display();
        switch (getInt("")) {

            case 0 -> restaurantManagementService.startRestaurantWork();
            case 1 -> restaurantManagementService.stopRestaurantWork();
            case 2 -> {
            }
            default -> {
                System.out.println("Please choose and option from 0 to " + (getRestaurantManagement().getActions().size() - 1));
                invokeAction();
            }
        }
    }
}
