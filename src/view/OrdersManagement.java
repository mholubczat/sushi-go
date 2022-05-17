package view;

public final class OrdersManagement extends TerminalScreen {
    private static OrdersManagement ordersManagement;

    private OrdersManagement(String message) {
        super(message);
    }

    public static OrdersManagement getOrdersManagement() {
        if (ordersManagement == null) {
            ordersManagement = new OrdersManagement("Choose one of the following actions:");

            ordersManagement.addAction("New order");
            ordersManagement.addAction("Pending orders");
            ordersManagement.addAction("Completed orders");
            ordersManagement.addAction("Display turnover");
            ordersManagement.addAction("Back to previous menu");
        }
        return ordersManagement;
    }

}
