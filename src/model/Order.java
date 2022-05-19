package model;

import model.employee.Employee;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import static java.time.format.DateTimeFormatter.ISO_TIME;
import static model.Menu.getCurrentMenu;

public abstract class Order implements Comparable<Order> {
    private final HashMap<MenuItem, Integer> orderItems = new HashMap<>();
    private final int orderNumber;
    private LocalDateTime orderTime;
    private boolean isCompleted = false;
    private boolean isDelayed = false;
    private BigDecimal value = BigDecimal.ZERO;

    private static int currentNumber;

    private static LocalDate workDay;

    private static final PriorityQueue<Order> pendingOrders = new PriorityQueue<>();

    private static final ArrayList<Order> finishedOrders = new ArrayList<>();
private static final PriorityBlockingQueue<Map.Entry<Integer,Order>> ordersToDeliver = new PriorityBlockingQueue<>();

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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isDelayed() {
        return isDelayed;
    }

    public void setDelayed(boolean delayed) {
        isDelayed = delayed;
    }

    public BigDecimal getValue() {
        return value;
    }

    public static PriorityQueue<Order> getPendingOrders() {
        return pendingOrders;
    }

    public static PriorityBlockingQueue<Map.Entry<Integer,Order>> getOrdersToDeliver() {
        return ordersToDeliver;
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
        if (isDelayed) sb.append(" ORDER DELAYED! ");
        sb.append(", value is ");
        sb.append(new DecimalFormat("0.00").format(value));

        return sb.toString();
    }


}