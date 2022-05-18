package model;


import model.employee.Cook;

import static model.employee.Employee.getEmployees;


public final class Kitchen{
    private static boolean isWorking = false; //TODO czy static??
    private IncomingOnlineOrders incomingOnlineOrders;

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    // zakładam że każdy kolejny kucharz nie zredukuje czasu przygotowania liniowo, przyjąłem t=To/n^(0,75) zgrubna estymacja
    // w grupie migają się od roboty! o głupotach gadajo
    public static long getCookSpeed() {
        return (long) (5000 / Math.pow(getEmployees().stream().filter(e -> e.getClass().equals(Cook.class)).count(), 0.75));
    }
}
/*
    @Override
    public void run() {
        startWorking(true);
    }

    public void startWorking(boolean working) {
        isWorking = working;
        prepareNextOrder();
        incomingOnlineOrders.start();
    }
    public void prepareNextOrder() {
        try {
            // nic do roboty? przerwa 5 sekund...
            if (toDoList().isEmpty()) {
                currentThread().wait(500);
                // fajrant? nie - to od nowa
                if(isWorking)prepareNextOrder();
            }

            if(isWorking) {
                Order topOrder = toDoList().get(0);
                currentThread().wait(getCookSpeed());
                topOrder.setCompleted(true);
            }

            if(isWorking)prepareNextOrder();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // zakładam że każdy kolejny kucharz zredukuje czas przygotowania, ale nie liniowo
    // w grupie migają się od roboty! o głupotach gadajo
    public static long getCookSpeed() {
        return (long) (5000 / Math.pow(getEmployees().stream().filter(e -> e.getClass().equals(Cook.class)).count(), 0.75));
    }
}*/