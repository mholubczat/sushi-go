package threads;

import model.OnlineOrder;
import service.OrdersService;

import static bootstrap.DataInitializer.getTestAddress;

public class IncomingOnlineOrders extends Thread{
    private boolean isWorking;

    @Override
    public void run() {
        super.run();
        receiveOrders();
    }

    private void receiveOrders() {
        try {
            Thread.sleep((long)(Math.random()*10000+15000));
            OrdersService.randomOrder(new OnlineOrder(getTestAddress()));
            if(isWorking) receiveOrders();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setWorking(boolean working) {
        this.isWorking = working;
    }
}
