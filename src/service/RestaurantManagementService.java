package service;

import static main.Kitchen.setWorking;
import static service.OrdersService.getOrdersService;

public final class RestaurantManagementService implements IRestaurantManagementService{

    private static RestaurantManagementService restaurantManagementService;



    public static RestaurantManagementService getRestaurantManagementService() {
        if (restaurantManagementService == null){
            restaurantManagementService = new RestaurantManagementService();
        }
        return restaurantManagementService;
    }

    @Override
    public void startRestaurantWork() {
        setWorking(true);
        System.out.println("Restaurant is working");
    }

    @Override
    public void stopRestaurantWork() {
        setWorking(false);
        System.out.println("Restaurant is not working");
    }

    @Override
    public void displayTurnover() {
        getOrdersService().displayTurnover();
    }
}
