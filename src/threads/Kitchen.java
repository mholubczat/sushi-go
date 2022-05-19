package threads;

import model.LocalOrder;
import model.Order;
import model.employee.Cook;

import java.util.AbstractMap;
import java.util.Map;

import static model.Order.getOrdersToDeliver;
import static model.Order.getPendingOrders;
import static model.employee.Cook.getCooks;
import static model.employee.Employee.getEmployees;


public class Kitchen extends Thread {
    private boolean isWorking = false;

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
        synchronized (getPendingOrders()){
            if (getPendingOrders().isEmpty()){
                System.out.println("czekam");
                    getPendingOrders().wait();
                System.out.println("jade");
            }}
        if (isWorking){
            ///co robim?
            nextOrder = getPendingOrders().poll();
            System.out.println(nextOrder);
            sleep(getCookSpeed());
            assert nextOrder != null;
            //https://www.baeldung.com/java-map-entry
            //https://stackoverflow.com/questions/20945984/is-there-blockingmap-as-blockingqueue-in-java
            if(nextOrder.getClass().equals(LocalOrder.class)) getOrdersToDeliver().add(new AbstractMap.SimpleEntry<>(1,nextOrder));
            else getOrdersToDeliver().add(new AbstractMap.SimpleEntry<>(2,nextOrder));
            getOrdersToDeliver().notify();
        }
        if (isWorking) prepareNextOrder();
}

public void setWorking(boolean working){
        isWorking=working;
        }

// zakładam że każdy kolejny kucharz nie zredukuje czasu przygotowania liniowo, przyjąłem t=To/n^(0,75) zgrubna estymacja
// w grupie migają się od roboty! o głupotach gadajo
private long getCookSpeed(){
        return(long)(50000/Math.pow(getCooks().size(),0.75));
        }
}
