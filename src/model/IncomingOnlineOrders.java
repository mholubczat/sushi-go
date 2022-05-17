package model;

import model.OnlineOrder;
import model.Order;

import static bootstrap.DataInitializer.getTestAddress;

public class IncomingOnlineOrders extends Thread{

    @Override
    public void run() {
        super.run();
        receiveOrders();
    }

    private void receiveOrders() {
        try {
            Thread.sleep((long)(Math.random()*1000+1500));
            Order.randomOrder(new OnlineOrder(getTestAddress()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
