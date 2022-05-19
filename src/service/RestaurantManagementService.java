package service;

import static threads.Kitchen.setWorking;

public class RestaurantManagementService implements IRestaurantManagementService {

    @Override
    public void startRestaurantWork() {
        setWorking(true);
    }

    @Override
    public void stopRestaurantWork() {
        setWorking(false);
    }
}
