package service;

import threads.IncomingOnlineOrders;
import threads.Kitchen;

public class RestaurantManagementService implements IRestaurantManagementService {
private static Kitchen kitchen;
private static IncomingOnlineOrders incomingOnlineOrders;

    public RestaurantManagementService() {

    }

    @Override
    public void startRestaurantWork() {
       kitchen = new Kitchen();
       kitchen.setWorking(true);
       kitchen.start();
    }

    @Override
    public void stopRestaurantWork() {
        kitchen.setWorking(false);
    }

    @Override
    public void startReceivingOnlineOrders() {
        incomingOnlineOrders = new IncomingOnlineOrders();
        incomingOnlineOrders.setWorking(true);
        incomingOnlineOrders.start();
    }

    @Override
    public void stopReceivingOnlineOrders() {
        incomingOnlineOrders.setWorking(false);
    }
}

