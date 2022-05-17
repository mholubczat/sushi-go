package service;

import model.Kitchen;

public class RestaurantManagementService implements IRestaurantManagementService{
private static Kitchen kitchen;

    public RestaurantManagementService(Kitchen kitchen) {
    this.kitchen = kitchen;
    }

    @Override
    public void startRestaurantWork() {
        kitchen.setWorking(true);
    }

    @Override
    public void stopRestaurantWork() {
        kitchen.setWorking(false);
    }
}
