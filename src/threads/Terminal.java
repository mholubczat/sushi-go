package threads;

import controller.*;
import service.*;

public class Terminal extends Thread {
    @Override
    public void run() {
        super.run();
        new MainMenuController(
                new EmployeeManagementController(new EmployeeManagementService()),
                new MenuManagementController(new MenuManagementService()),
                new RestaurantManagementController(new RestaurantManagementService()),
                new OrdersController(new OrdersService())
        ).invokeAction();
    }
}


