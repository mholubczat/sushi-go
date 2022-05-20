package threads;

import model.Order;
import model.employee.Waiter;

import java.util.Comparator;
import static model.Order.*;

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
        if (getInspectMode()) System.out.println(nextTable + " will be delivered");
        getWaiterList().sort(Comparator.comparing(Waiter::getLastTableTime));
        Waiter waiter = getWaiterList().get(0);
        synchronized(waiter){
            if (getInspectMode()) System.out.println(waiter + " will be delivering");
            waiter.getExecutor().submit(

                    () -> {
                        try {
                            Thread.sleep(120000/speedUp/speedUp);
                            waiter.completeOrder(nextTable);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
        if (isWorking() || getTablesToServe().size() != 0)
            startWaiterService();
    }

}
