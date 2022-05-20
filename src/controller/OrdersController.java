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
            case 2 -> ordersService.showServedOrders();
            case 3 -> ordersService.showDeliveredOrders();
            case 4 -> ordersService.showCompletedOrders();
            case 5 -> ordersService.displayTurnover();
            case 6 -> {
            }
            default ->
                    System.out.println("Please choose and option from 0 to " + (getOrdersManagement().getActions().size()-1));
        }
    }
}
