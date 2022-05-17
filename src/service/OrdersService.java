package service;

import model.*;
import view.DisplayMenu;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

import static model.Menu.getCurrentMenu;
import static model.Order.getOrders;
import static utils.InputValidator.*;

public final class OrdersService implements IOrdersService {

    public static Comparator<Order> toDo = (o1, o2) -> {
        if (o1.getClass().equals(o2.getClass())) return o1.getOrderTime().compareTo(o2.getOrderTime());
        else return o2.getClass().equals(LocalOrder.class) ? 1 : -1;
    };

    public static List<Order> toDoList() {
        return getOrders().stream().filter(Predicate.not(Order::isCompleted)).sorted(toDo).toList();
    }

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
    }

    @Override
    public void showPendingOrders() {
        AtomicBoolean flag = new AtomicBoolean(false);
        toDoList().forEach(order -> {
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
                .forEach(System.out::println);

        System.out.println("No more orders");
    }

    @Override
    public void displayTurnover() {
        LocalDate date = getLocalDate("Please enter a date in yyyy-mm-dd format");
        BigDecimal turnover =
                getOrders().stream().filter(order -> order.getOrderTime().toLocalDate().equals(date))
                        .filter(Order::isCompleted).map(Order::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Turnover for " + date + " is " + turnover);
    }
}
// TODO order value rounding + display order details