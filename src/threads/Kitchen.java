package threads;

import model.Order;
import model.employee.Cook;


import static model.Order.getPendingOrders;
import static model.employee.Employee.getEmployees;


public class Kitchen extends Thread {
    private static boolean isWorking = false;

    @Override
    public void run() {
        super.run();
        try {
            startWorking(isWorking);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void startWorking(boolean working) throws InterruptedException {
        isWorking = working;
        prepareNextOrder();
    }
    public void setWorking(Boolean isWorking){
        this.isWorking=isWorking;
    }

    public static void prepareNextOrder() throws InterruptedException {
        Order nextOrder;
        synchronized (getPendingOrders()) {
            while (getPendingOrders().isEmpty())
                try {
                    getPendingOrders().wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            nextOrder = getPendingOrders().poll();
        }
        if (isWorking) {

            sleep(getCookSpeed());
            nextOrder.setCompleted(true);
        }
        if (isWorking) prepareNextOrder();

}


public static void setWorking(boolean working){
        isWorking=working;
        }

// zakładam że każdy kolejny kucharz nie zredukuje czasu przygotowania liniowo, przyjąłem t=To/n^(0,75) zgrubna estymacja
// w grupie migają się od roboty! o głupotach gadajo
private static long getCookSpeed(){
        return(long)(5000/Math.pow(getEmployees().stream().filter(e->e.getClass().equals(Cook.class)).count(),0.75));
        }
}
