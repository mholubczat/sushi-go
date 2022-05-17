package service;

import model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicBoolean;

import static model.Menu.getMenu;
import static model.Order.getOrders;
import static view.InputValidator.*;
import static view.RestaurantMenu.getRestaurantMenu;

public final class OrdersService implements IOrdersService {
    private static OrdersService ordersService;

    public static OrdersService getOrdersService() {
        if (ordersService == null) {
            ordersService = new OrdersService();
        }
        return ordersService;
    }

    @Override
    public void addOrder() {
        Order newOrder;
        if (getBoolean("Is the order online?")) {
            newOrder = new OnlineOrder(new Address(getString("Enter street name"), getString("Enter street number"), getString("Enter flat number if applicable"), getString("Enter postal code"), getString("Enter city")));
        } else {
            newOrder = new LocalOrder(getInt("Enter table number"));
        }
        boolean loop = true;
        while (loop) {
            getRestaurantMenu().display();
            MenuItem menuItem = getMenu().getCurrentMenu().get(getInt("Enter item number"));
            while (!menuItem.isAvailable())
                menuItem = getMenu().getCurrentMenu().get(getInt("Item not available, try again!"));
            newOrder.addOrderItem(menuItem, getInt("Enter quantity"));
            loop = getBoolean("Next item? Press Y or N");
        }

    }

    @Override
    public void showPendingOrders() {
        AtomicBoolean flag = new AtomicBoolean(false);
        getOrders().stream().filter(order -> !order.isCompleted())
                .forEach(order -> {
                    flag.set(true);
                    System.out.println(order);
                });
        if (!flag.get()) System.out.println("Nothing to display: all orders have been completed!\n");
    }

    @Override
    public void showCompletedOrders() {

        LocalDate date = getLocalDate("Please enter a date in yyyy-mm-dd format");

        getOrders().stream().filter(order -> order.getOrderTime().toLocalDate().equals(date))
                .filter(Order::isCompleted)
                .forEach(order -> System.out.println(order));

        System.out.println("No more orders");
    }

    @Override
    public void displayTurnover() {
        LocalDate date = getLocalDate("Please enter a date in yyyy-mm-dd format");
        BigDecimal turnover =
                getOrders().stream().filter(order -> order.getOrderTime().toLocalDate().equals(date))
                        .filter(Order::isCompleted).map(Order::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Turnover for " + date + " was " + turnover);
    }
}
