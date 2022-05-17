package controller;

import model.LocalOrder;
import model.Order;
import service.IOrdersService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import static model.Order.getOrders;
import static service.OrdersService.getOrdersService;
import static view.EmployeeManagement.getEmployeeManagement;
import static view.InputValidator.getInt;
import static view.MainMenu.getMainMenu;
import static view.MenuManagement.getMenuManagement;
import static view.OrdersManagement.getOrdersManagement;
import static view.RestaurantManagement.getRestaurantManagement;

public final class OrdersController implements TerminalEntryController{
    private static OrdersController ordersController;
    private final IOrdersService ordersService;

    public OrdersController(IOrdersService ordersService) {
        this.ordersService = ordersService;
    }

    public static OrdersController getOrdersController() {
        if (ordersController == null) {
            ordersController = new OrdersController(getOrdersService());
        }
        return ordersController;
    }

    @Override
    public void invokeAction() {
        getOrdersManagement().display();
        switch (getInt("")) {

            case 0 -> ordersService.addOrder();
            case 1 -> ordersService.showPendingOrders();
            case 2 -> ordersService.showCompletedOrders();
            case 3 -> ordersService.displayTurnover();
            case 4 -> {
            }
            default ->
                    System.out.println("Please choose and option from 0 to " + (getOrdersManagement().getActions().size()-1));
        }
    }
}
