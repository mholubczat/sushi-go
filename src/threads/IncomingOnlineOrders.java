package threads;

import model.OnlineOrder;
import service.OrdersService;

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
            OrdersService.randomOrder(new OnlineOrder(getTestAddress()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
