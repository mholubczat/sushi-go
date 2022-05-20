package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.util.concurrent.PriorityBlockingQueue;

import static view.DisplayMenu.getInspectMode;

public abstract class Order implements Comparable<Order> {
    private final HashMap<MenuItem, Integer> orderItems = new HashMap<>();
    private final int orderNumber;
    private LocalDateTime orderTime;
    private boolean isCompleted = false;
    private boolean isPrepared = false;
    private boolean isDelayed = false;
    private BigDecimal value = BigDecimal.ZERO.setScale(2, RoundingMode.CEILING);

    private static int currentNumber;

    private static LocalDate workDay;

    private static final PriorityQueue<Order> pendingOrders = new PriorityQueue<>();

    private static final LinkedList<Order> delayQueue = new LinkedList<>();

    private static final ArrayList<Order> finishedOrders = new ArrayList<>();
    private static final PriorityBlockingQueue<Order> ordersToDeliver = new PriorityBlockingQueue<>();
    private static final PriorityBlockingQueue<Order> ordersToGiveOut = new PriorityBlockingQueue<>();

    public Order() {

        this.orderTime = LocalDateTime.now();
        if (workDay == null || !workDay.equals(LocalDate.now())) {
            currentNumber = 1;
            workDay = LocalDate.now();
        }
        this.orderNumber = currentNumber++;
    }

    @Override
    public int compareTo(Order o) {
        if (isDelayed != o.isDelayed()) {
            if (isDelayed) return -1;
            else return 1;
        }
        if (getClass().equals(o.getClass())) {
            return getOrderTime().compareTo(o.getOrderTime());
        } else return o.getClass().equals(LocalOrder.class) ? 1 : -1;
    }

    public void addOrderItem(MenuItem menuItem, Integer quantity) {
        orderItems.put(menuItem, quantity);
        value = value.add(menuItem.getPrice().multiply(BigDecimal.valueOf(quantity)));
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public static ArrayList<Order> getFinishedOrders() {
        return finishedOrders;
    }

    public boolean isPending() {
        return !isCompleted;
    }

    public void setCompleted() {
        isCompleted = true;
        finishedOrders.add(this);
    }

    public boolean isDelayed() {
        return isDelayed;
    }

    public void setDelayed(boolean delayed) {
        isDelayed = delayed;
        // 50% szans na bunt
        if (Math.random() < 0.5) {
            this.setCompleted();
            this.setValue(BigDecimal.ZERO);
            if(getInspectMode()) System.out.println("Delayed order refused! Setting value to 0, order will not be prepared " + this);
            if (!isPrepared) pendingOrders.remove(this);
        } else {
            this.setValue(this.getValue().multiply(BigDecimal.valueOf(0.8)));
            if(getInspectMode()) System.out.println("Delayed order - reducing value by 20%, order re-added to PriorityQueue " + this);
            if (!isPrepared) {
                // zmiana priorytetu przygotowania
                pendingOrders.remove(this);
                pendingOrders.add(this);
            }
        }
    }

    public BigDecimal getValue() {
        return value;
    }

    public static PriorityQueue<Order> getPendingOrders() {
        return pendingOrders;
    }

    public static LinkedList<Order> getDelayQueue() {
        return delayQueue;
    }

    public static PriorityBlockingQueue<Order> getTablesToServe() {
        return ordersToGiveOut;
    }

    public static PriorityBlockingQueue<Order> getOrdersToDeliver() {
        return ordersToDeliver;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public HashMap<MenuItem, Integer> getOrderItems() {
        return orderItems;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" number ");
        sb.append(orderNumber);
        sb.append(", ordered today at ");
        sb.append(orderTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        if (isDelayed) sb.append(" ORDER DELAYED!");
        sb.append(", value is ");
        // kolejna linijka poprawia wyświetlanie 6.3 -> 6.30
        sb.append(new DecimalFormat("0.00").format(value));

        return sb.toString();
    }


    public boolean isPrepared() {
        return isPrepared;
    }

    public void setPrepared() {
        isPrepared = true;
        if (this.getClass().equals(LocalOrder.class)) ordersToGiveOut.add(this);
        else ordersToDeliver.add(this);
    }
}