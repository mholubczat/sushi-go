package controller;

import service.IOrdersService;

import static utils.InputValidator.getInt;
import static view.OrdersManagement.getOrdersManagement;

public final class OrdersController {
    private final IOrdersService ordersService;

    public OrdersController(IOrdersService ordersService) {
        this.ordersService = ordersService;
    }

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
