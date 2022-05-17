package controller;


import service.IMenuManagementService;

import static service.MenuManagementService.getMenuManagementService;
import static view.InputValidator.getInt;
import static view.MenuManagement.getMenuManagement;
import static view.OrdersManagement.getOrdersManagement;

public final class MenuManagementController {
    private static MenuManagementController menuManagementController;
    private final IMenuManagementService menuManagementService;

    public static MenuManagementController getMenuManagementController() {
        if (menuManagementController == null) {
            menuManagementController = new MenuManagementController(getMenuManagementService());
        }
        return menuManagementController;
    }

    public MenuManagementController(IMenuManagementService menuManagementService) {
        this.menuManagementService = menuManagementService;
    }

    public void invokeAction() {
        getMenuManagement().display();
        switch (getInt("")) {

            case 0 -> menuManagementService.showMenu();
            case 1 -> menuManagementService.addMenuItem();
            case 2 -> menuManagementService.removeMenuItem();
            case 3 -> menuManagementService.makeMenuItemAvailable();
            case 4 -> menuManagementService.makeMenuItemNotAvailable();
            case 5 -> menuManagementService.exportMenu();
            case 6 -> menuManagementService.importMenu();
            case 7 -> {
            }
            default -> {
                System.out.println("Please choose and option from 0 to " + (getOrdersManagement().getActions().size() - 1));
                invokeAction();
            }
        }
    }
}
