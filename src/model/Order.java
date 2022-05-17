package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static model.Menu.getCurrentMenu;
import static service.OrdersService.toDoList;

public abstract class Order {
    private HashMap<MenuItem, Integer> orderItems = new HashMap<>();
    private final int orderNumber;
    private LocalDateTime orderTime;
    private boolean isCompleted = false;
    private boolean isDelayed = false;
    private BigDecimal value = BigDecimal.ZERO;
    private static final List<Order> orders = new ArrayList<>();


    public Order() {
        this.orderTime = LocalDateTime.now();
        this.orderNumber = orders.size() == 0 ? 1 :
                orders.get(orders.size() - 1).getOrderTime().toLocalDate().equals(orderTime.toLocalDate()) //czy pierwszy danego dnia?
                        ? orders.get(orders.size() - 1).getOrderNumber() + 1
                        : 1;
        orders.add(this);

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

    public static List<Order> getOrders() {
        return orders;
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

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(toDoList().indexOf(this));
        sb.append("] - ");
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
        sb.append(orders.indexOf(this));

        return sb.toString();
    }
}