package threads;

import model.OnlineOrder;
import model.Order;
import model.employee.Cook;
import model.employee.Deliverer;
import model.employee.Employee;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static model.Order.getOrdersToDeliver;
import static model.employee.Deliverer.getDelivererList;
import static model.employee.Employee.getEmployees;
import static model.employee.Waiter.getWaiterList;

public abstract class DeliveryService<T> extends Thread {

    ThreadPoolExecutor threadPoolExecutor;
    //https://www.baeldung.com/thread-pool-java-and-guava
    private List<T> executorList;

    public DeliveryService() {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(executorList.size());
    }

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
        synchronized (getOrdersToDeliver()) {

            threadPoolExecutor.submit(getOrdersToDeliver().take())
                    () -> {
                        try {
                            Thread.sleep(120000);

                            getOrdersToDeliver().take().getValue().setCompleted(true);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );


        }
        startDelivery();
    }

}
