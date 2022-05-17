package view;

public final class RestaurantManagement extends TerminalEntry{
    private static RestaurantManagement restaurantManagement;

    public RestaurantManagement(String message) {
        super(message);
    }

    public static RestaurantManagement getRestaurantManagement(){
        if (restaurantManagement == null) {
            restaurantManagement = new RestaurantManagement("Choose one of the following actions:");

            restaurantManagement.addAction("Start/resume restaurant work");
            restaurantManagement.addAction("Stop restaurant work");
            restaurantManagement.addAction("Display turnover");
            restaurantManagement.addAction("Back to previous menu");
        }
        return restaurantManagement;
    }
}
