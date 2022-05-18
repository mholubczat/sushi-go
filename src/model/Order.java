package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static model.Menu.getCurrentMenu;

public abstract class Order implements Comparable<Order>{
    private HashMap<MenuItem, Integer> orderItems = new HashMap<>();
    private final int orderNumber;
    private LocalDateTime orderTime;
    private boolean isCompleted = false;
    private boolean isDelayed = false;
    private BigDecimal value = BigDecimal.ZERO;

    private static int currentNumber;

    private static LocalDate workDay;

    private static final PriorityQueue<Order> orderPriorityQueue = new PriorityQueue<>();

    private static final ArrayList<Order> finishedOrders = new ArrayList<>();


    public Order() {

        this.orderTime = LocalDateTime.now();
        if (workDay==null || !workDay.equals(LocalDate.now())) {
            currentNumber = 1;
            workDay = LocalDate.now();
        }
        this.orderNumber = currentNumber++;
        orderPriorityQueue.add(this);
    }

    @Override
    public int compareTo(Order o) {
        if(getClass().equals(o.getClass())) return getOrderTime().compareTo(o.getOrderTime());
        else return o.getClass().equals(LocalOrder.class) ? 1 : -1;
    }

    public static void randomOrder(Order order) {
        for (int i = 0; i < (int) (Math.random() * 5 + 1); i++) {
            order.addOrderItem(getCurrentMenu().get((int) (Math.random() * getCurrentMenu().size())), ((int) (Math.random() * 2) + 1));
            order.setOrderTime(LocalDateTime.now().minusMinutes((long) (Math.random() * 10)));
        }
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

    public static PriorityQueue<Order> getOrderPriorityQueue(){
        return orderPriorityQueue;
    }

    public static ArrayList<Order> getFinishedOrders(){
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
        sb.append(orderTime.getHour());
        sb.append(":");
        sb.append(orderTime.getMinute());
        if (isDelayed) sb.append(" ORDER DELAYED! ");
        sb.append(", value is ");
        sb.append(value);

        return sb.toString();
    }
}