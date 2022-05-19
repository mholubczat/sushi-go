package view;

public final class RestaurantManagement extends TerminalScreen {
    private static RestaurantManagement restaurantManagement;

    private RestaurantManagement(String message) {
        super(message);
    }

    public static RestaurantManagement getRestaurantManagement() {
        if (restaurantManagement == null) {
            restaurantManagement = new RestaurantManagement("Choose one of the following actions:");

            restaurantManagement.addAction("Start restaurant work");
            restaurantManagement.addAction("Stop restaurant work");
            restaurantManagement.addAction("Start receiving online orders");
            restaurantManagement.addAction("Stop receiving online orders");
            restaurantManagement.addAction("Back to previous menu");
        }
        return restaurantManagement;
    }
}
