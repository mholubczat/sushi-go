package threads;

import model.Order;
import model.employee.Deliverer;

import java.time.LocalDateTime;
import java.util.Comparator;

import static model.Order.getOrdersToDeliver;
import static model.employee.Deliverer.getDelivererList;
import static threads.Kitchen.isWorking;
import static threads.Kitchen.speedUp;
import static view.DisplayMenu.getInspectMode;

public class DeliveryService extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            startDelivery();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void startDelivery() throws InterruptedException {

        Order nextDelivery = getOrdersToDeliver().take();
        System.out.println(nextDelivery);
        getDelivererList().sort(Comparator.comparing(Deliverer::getLastDeliveryTime));
        Deliverer deliverer = getDelivererList().get(0);
        deliverer.setLastDeliveryTime(LocalDateTime.now());
        synchronized (deliverer) {
            deliverer.getExecutor().submit(
                    () -> {
                        if (getInspectMode()) System.out.println(deliverer + " delivers " + nextDelivery);
                        try {
                            Thread.sleep(120000 / speedUp);
                            deliverer.completeOrder(nextDelivery);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (getInspectMode()) System.out.println(deliverer + " finished delivery of " + nextDelivery);
                    }
            );
        }
        if (isWorking() || getOrdersToDeliver().size() != 0)
            startDelivery();
    }

}
