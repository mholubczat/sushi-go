package model.employee;

import model.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static view.DisplayMenu.getInspectMode;

public class Waiter extends Employee {
    private final static ArrayList<Waiter> waiterList = new ArrayList<>();
    private LocalDateTime lastTableTime = LocalDateTime.now();
    private BigDecimal totalTip = BigDecimal.ZERO;
    private BigDecimal todayTip = BigDecimal.ZERO;
    private int tablesToday = 0;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public Waiter(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);
        waiterList.add(this);
    }
    public LocalDateTime getLastTableTime() {
        return lastTableTime;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public void setLastTableTime(LocalDateTime lastDeliveryTime) {
        this.lastTableTime = lastDeliveryTime;
    }

    public static ArrayList<Waiter> getWaiterList() {
        return waiterList;
    }

    public void completeOrder(Order order) {
        BigDecimal tip = getTip(order).setScale(2,RoundingMode.CEILING);
        if (getInspectMode()) System.out.println("Tip for " + order + " is " + tip);
        totalTip = totalTip.add(tip);
        if (lastTableTime.toLocalDate().equals(LocalDate.now())) {
            tablesToday++;
            todayTip = todayTip.add(tip);
        } else {todayTip = tip;
        tablesToday = 1;}
        if(order.isPending()) {
            order.setCompleted();
        }
        setLastTableTime(LocalDateTime.now());
    }

    @Override
    public String toString() {

        return super.toString() +
                " today served " +
                tablesToday +
                " tables, tipped " +
                todayTip;
    }
}
