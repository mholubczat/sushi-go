package threads;

import model.Order;
import model.employee.Waiter;

import java.time.LocalDateTime;
import java.util.Comparator;
import static model.Order.*;

import static model.employee.Deliverer.getDelivererList;
import static model.employee.Waiter.getWaiterList;
import static threads.Kitchen.isWorking;
import static threads.Kitchen.speedUp;
import static view.DisplayMenu.getInspectMode;

public class WaiterService extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            startWaiterService();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void startWaiterService() throws InterruptedException {

        Order nextTable = getTablesToServe().take();
        getWaiterList().sort(Comparator.comparing(Waiter::getLastTableTime));
        Waiter waiter = getWaiterList().get(0);
        waiter.setLastTableTime(LocalDateTime.now());
        synchronized(waiter){
            waiter.getExecutor().submit(
                    () -> {
                        if (getInspectMode()) System.out.println(waiter + " ---- SERVES ---- " + nextTable);
                        try {
                            Thread.sleep(120000/speedUp);
                            waiter.completeOrder(nextTable);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (getInspectMode()) System.out.println(waiter + " ---- FINISHED ---- " + nextTable);
                    }
            );
        }
        if (isWorking() || getTablesToServe().size() != 0)
            startWaiterService();
    }

}
