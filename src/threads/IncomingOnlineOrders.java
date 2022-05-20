package threads;

import model.OnlineOrder;
import model.Order;
import service.OrdersService;

import static bootstrap.DataInitializer.getTestAddress;
import static model.Order.getDelayQueue;
import static threads.Kitchen.speedUp;
import static view.DisplayMenu.getInspectMode;

public class IncomingOnlineOrders extends Thread {
    private static boolean isOnlineWorking;

    @Override
    public void run() {
        super.run();
        receiveOrders();
    }

    private void receiveOrders() {
        try {

            Thread.sleep((long) (Math.random() * 10000/speedUp + 15000/speedUp));
            synchronized(getDelayQueue()){
            Order order = OrdersService.randomOrder(new OnlineOrder(getTestAddress()),false);
            if (getInspectMode()) System.out.println("Random online order received! " + order);}
            if (isOnlineWorking) receiveOrders();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setWorking(boolean working) {
        isOnlineWorking = working;
    }

    public static boolean isOnlineWorking() {
        return isOnlineWorking;
    }
}
