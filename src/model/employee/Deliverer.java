package model.employee;

import model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static view.DisplayMenu.getInspectMode;

public class Deliverer extends Employee {
    private final static ArrayList<Deliverer> delivererList = new ArrayList<>();
    private LocalDateTime lastDeliveryTime = LocalDateTime.now();
    private BigDecimal totalTip = BigDecimal.ZERO;
    private BigDecimal todayTip = BigDecimal.ZERO;
    private int deliveriesToday = 0;
    private int deliveriesTotal = 0;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public Deliverer(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);
        delivererList.add(this);
    }

    public BigDecimal getTotalTip() {
        return totalTip;
    }


    public BigDecimal getTodayTip() {
        return todayTip;
    }

    public int getDeliveriesToday() {
        return deliveriesToday;
    }

    public void setDeliveriesToday(int deliveriesToday) {
        this.deliveriesToday = deliveriesToday;
    }

    public int getDeliveriesTotal() {
        return deliveriesTotal;
    }

    public LocalDateTime getLastDeliveryTime() {
        return lastDeliveryTime;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public void setLastDeliveryTime(LocalDateTime lastDeliveryTime) {
        this.lastDeliveryTime = lastDeliveryTime;
    }

    public static ArrayList<Deliverer> getDelivererList() {
        return delivererList;
    }


    public void completeOrder(Order order) {
        BigDecimal tip = getTip(order);
        if (getInspectMode()) System.out.println("Tip for " + order + " is " + tip);
        totalTip = totalTip.add(tip);
        deliveriesTotal = deliveriesToday + 1;
        if (lastDeliveryTime.toLocalDate().equals(LocalDate.now())) {
            deliveriesToday = deliveriesToday + 1;
            todayTip = todayTip.add(tip);
        } else {
            todayTip = tip;
            deliveriesToday = 1;
        }
        if (order.isPending()) {
            order.setCompleted();
        }
        setLastDeliveryTime(LocalDateTime.now());
    }

    @Override
    public String toString() {

        return super.toString() +
                " today delivered " +
                deliveriesToday +
                " orders, tipped " +
                todayTip;
    }
}
