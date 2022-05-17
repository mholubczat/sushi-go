package view;

import model.MenuItem;
import model.employee.Cook;

import java.util.ArrayList;

import static model.Menu.getMenu;

public final class ProfessionMenu extends TerminalEntry{
    private static ProfessionMenu professionMenu;

    public ProfessionMenu(String message) {
        super(message);
    }

    public static ProfessionMenu getProfessionMenu() {
        if (professionMenu == null) {
            professionMenu = new ProfessionMenu("Enter profession number:");

            professionMenu.addAction("Cook");
            professionMenu.addAction("Waiter");
            professionMenu.addAction("Delivery");
        }
        return professionMenu;
    }

}
