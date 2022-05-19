package service;

import model.*;
import view.DisplayMenu;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
        synchronized (getPendingOrders()) {
            getPendingOrders().add(newOrder);
            getPendingOrders().notify();
        }
    }

    public static void randomOrder(Order order) {
        for (int i = 0; i < (int) (Math.random() * 5 + 1); i++) {
            order.addOrderItem(getCurrentMenu().get((int) (Math.random() * getCurrentMenu().size())), ((int) (Math.random() * 2) + 1));
        }
        order.setOrderTime(LocalDateTime.now().minusMinutes((long) (Math.random() * 10)));
        synchronized (getPendingOrders()) {
            getPendingOrders().add(order);
            getPendingOrders().notify();
        }
    }

    @Override
    public void showPendingOrders() {
        synchronized (getPendingOrders()){
        getPendingOrders().forEach(System.out::println);
        getPendingOrders().notify();
        }
    }

    @Override
    public void showCompletedOrders() {
        LocalDate date = getLocalDate("Please enter a date in yyyy-mm-dd format");
        getFinishedOrders().stream().filter(order -> order.getOrderTime().toLocalDate().equals(date)).forEach(System.out::println);
    }

    @Override
    public void displayTurnover() {
        LocalDate date = getLocalDate("Please enter a date in yyyy-mm-dd format");
        BigDecimal turnover =
                getFinishedOrders().stream().filter(order -> order.getOrderTime().toLocalDate().equals(date))
                        .map(Order::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Turnover for " + date + " is " + turnover);
    }
}
// TODO display order details