package service;

import model.*;
import view.DisplayMenu;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Stream;

import static model.Menu.getCurrentMenu;
import static model.Order.*;
import static utils.InputValidator.*;

public final class OrdersService implements IOrdersService {

    @Override
    public void addOrder() {
        DisplayMenu currentMenu = new DisplayMenu("Currently in menu"); //prepare terminal menu screen
        Order newOrder;
        if (getBoolean("Is the order online? Enter Y or N")) {
            newOrder = new OnlineOrder(new Address(getString("Enter street name"), getString("Enter street number"), getString("Enter flat number if applicable"), getString("Enter postal code"), getString("Enter city")));
        } else {
            newOrder = new LocalOrder(getInt("Enter table number"));
        }
        boolean loop = true;
        while (loop) {
            currentMenu.display();
            MenuItem menuItem = getCurrentMenu().get(getInt("Enter item number"));
            while (!menuItem.isAvailable())
                menuItem = getCurrentMenu().get(getInt("Item not available, try again!"));
            newOrder.addOrderItem(menuItem, getInt("Enter quantity"));
            loop = getBoolean("Next item? Enter Y or N");
        }
        getDelayQueue().add(newOrder);
        synchronized (getPendingOrders()) {
            getPendingOrders().add(newOrder);
            getPendingOrders().notify();
        }
    }

    public static Order randomOrder(Order order, boolean initial) {
        for (int i = 0; i < (int) (Math.random() * 5 + 1); i++) {
            order.addOrderItem(getCurrentMenu().get((int) (Math.random() * getCurrentMenu().size())), ((int) (Math.random() * 2) + 1));
        }
        if(initial) order.setOrderTime(LocalDateTime.now().minusMinutes((long) (Math.random() * 30)));
        getDelayQueue().add(order);
        synchronized (getPendingOrders()) {
            getPendingOrders().add(order);
            getPendingOrders().notify();
        }
        return order;
    }



    @Override
    public void showPendingOrders() {
        System.out.println("Orders waiting to be prepared");
        getPendingOrders().forEach(System.out::println);
        boolean details = getBoolean("Show order details? Y/N");
        if (details) orderDetails(getPendingOrders());
    }

    @Override
    public void showServedOrders() {
        System.out.println("Orders waiting to be served");
        getTablesToServe().forEach(System.out::println);
        boolean details = getBoolean("Show order details? Y/N");
        if (details) orderDetails(getTablesToServe());
    }

    @Override
    public void showDeliveredOrders() {
        System.out.println("Orders waiting to be delivered");
        getOrdersToDeliver().forEach(System.out::println);
        boolean details = getBoolean("Show order details? Y/N");
        if (details) orderDetails(getOrdersToDeliver());
    }

    @Override
    public void showCompletedOrders() {
        LocalDate date = getLocalDate("Please enter a date in yyyy-mm-dd format");
        getFinishedOrders().stream().filter(order -> order.getOrderTime().toLocalDate().equals(date)).forEach(System.out::println);
        boolean details = getBoolean("Show order details? Y/N");
        if (details) orderDetails(getFinishedOrders(), date);
    }

    @Override
    public void displayTurnover() {
        LocalDate date = getLocalDate("Please enter a date in yyyy-mm-dd format");
        BigDecimal turnover =
                getFinishedOrders().stream().filter(order -> order.getOrderTime().toLocalDate().equals(date))
                        .map(Order::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Turnover for " + date + " is " + turnover);
    }

    public void orderDetails(ArrayList<Order> list, LocalDate date) {
        int orderToDisplay = getInt("Select order to display");
        List<Order> order = getFinishedOrders().stream().filter(o -> o.getOrderTime().toLocalDate().equals(date))
                .filter(o -> o.getOrderNumber() == orderToDisplay).toList();
        displayOrderList(order);
    }

    private void displayOrderList(List<Order> order) {
        if(order.get(0)!=null) {
            System.out.println(order.get(0));
            for (MenuItem mi: order.get(0).getOrderItems().keySet()) {
                System.out.printf("%-4s%-3s%-22s%-6s%-5s%n", "[" + getCurrentMenu().indexOf(mi) + "]"," -", mi.getName(), getPriceFormat().format(mi.getPrice()),"zł" + " quantity " + order.get(0).getOrderItems().get(mi));
            }
        }
    }

    public void orderDetails(PriorityQueue<Order> priorityQueue) {
        displayOrderStream(priorityQueue.stream(), priorityQueue);
    }

    private void displayOrderStream(Stream<Order> stream, Queue<Order> priorityQueue) {
        int orderToDisplay = getInt("Select order to display");
        List<Order> order = stream
                .filter(o -> o.getOrderNumber() == orderToDisplay).toList();
        displayOrderList(order);
    }

    public void orderDetails(PriorityBlockingQueue<Order> priorityQueue) {
        displayOrderStream(priorityQueue.stream(), priorityQueue);
    }
}
// TODO display order details