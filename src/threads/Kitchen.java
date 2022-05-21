package threads;

import model.Order;


import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

import static model.Order.getDelayQueue;
import static model.Order.getPendingOrders;
import static model.employee.Cook.getCooks;
import static view.DisplayMenu.getInspectMode;


public class Kitchen extends Thread {
    private static boolean isWorking = false;
    static long speedUp = 1;
    private LocalDateTime nextCheck;

    @Override
    public void run() {
        super.run();
        try {
            prepareNextOrder();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepareNextOrder() throws InterruptedException {
        Order nextOrder;
        // czekam na zamówienia
        synchronized (getPendingOrders()) {
            if (getPendingOrders().isEmpty()) {

                if (getInspectMode()) System.out.println("Kitchen has no more orders to process");
                getPendingOrders().wait();
                if (getInspectMode()) System.out.println("Kitchen resumed work");

                // ogarnij opóźnione
            } else if (nextCheck == null || nextCheck.isBefore(LocalDateTime.now())) {
                if (getInspectMode()) System.out.println("Checking for delayed orders");
                handleDelays();

            }

            if (isWorking) {
                nextOrder = getPendingOrders().poll();
                if (getInspectMode()) System.out.println("Kitchen prepares order " + nextOrder);
                sleep(getCookSpeed());
                assert nextOrder != null;
                nextOrder.setPrepared();
            }
            if (isWorking) prepareNextOrder();
        }
    }

    public void handleDelays() {
            LinkedList<Order> delayQueue = getDelayQueue();
            while (delayQueue.peek() != null) {
                if (delayQueue.peek().isPrepared()) {
                    delayQueue.poll();
                } else {
                    assert delayQueue.peek() != null;
                    if (delayQueue.peek().getOrderTime().isAfter(LocalDateTime.now().minusMinutes(15/speedUp))) break;
                    if (getInspectMode()) System.out.println("Delayed order found " + delayQueue.peek());
                    Objects.requireNonNull(delayQueue.poll()).setDelayed(true);
                }
            }
        nextCheck = LocalDateTime.now().plusMinutes(14/speedUp);
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    // zakładam że każdy kolejny kucharz nie zredukuje czasu przygotowania liniowo, przyjąłem t=To/n^(0,75) zgrubna estymacja

    private static long getCookSpeed() {
        return (long) (50000 / speedUp / Math.pow(getCooks().size(), 0.75));
    }

    public static boolean isWorking() {
        return isWorking;
    }

    public static void setSpeedUp(long l) {
        speedUp = l;
    }
}
