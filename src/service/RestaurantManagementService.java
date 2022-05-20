package service;

import threads.DeliveryService;
import threads.IncomingOnlineOrders;
import threads.Kitchen;
import threads.WaiterService;

import static threads.IncomingOnlineOrders.isOnlineWorking;
import static threads.Kitchen.isWorking;

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
        //Zakładam że personel wydaje przygotowane posiłki nawet jeśli kuchnia już nie pracuje

        DeliveryService deliveryService = new DeliveryService();
        deliveryService.start();
        WaiterService waiterService = new WaiterService();
        waiterService.start();
    }

    @Override
    public void stopRestaurantWork() {
        if (kitchen != null && isWorking())
            kitchen.setWorking(false);
        else System.out.println("Kitchen is already not working");
    }

    @Override
    public void startReceivingOnlineOrders() {
        incomingOnlineOrders = new IncomingOnlineOrders();
        incomingOnlineOrders.setWorking(true);
        incomingOnlineOrders.start();
    }

    @Override
    public void stopReceivingOnlineOrders() {
        if (incomingOnlineOrders != null && isOnlineWorking())
            incomingOnlineOrders.setWorking(false);
        else System.out.println("Already not receiving orders");
    }
}

