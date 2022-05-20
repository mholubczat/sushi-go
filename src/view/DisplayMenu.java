package view;

import model.MenuItem;

import static model.Menu.getCurrentMenu;
import static utils.InputValidator.getPriceFormat;

public class DisplayMenu extends TerminalScreen {
    private static boolean inspectMode = false;

    public DisplayMenu(String message) {
        super(message);

        for (MenuItem menuItem : getCurrentMenu()) {
            if (!menuItem.isAvailable())
                this.addAction("POSITION NOT AVAILABLE - " + (menuItem.getName() + "\t\t\t" + menuItem.getDescription() + "\t\t\t" + menuItem.getPrice() + "zł"));
            else
                this.addAction((menuItem.getName() + "\t\t\t" + menuItem.getDescription() + "\t\t\t" + menuItem.getPrice() + "zł"));
        }
    }

    @Override
    public void display() {
        System.out.println(this.getMessage());
        if (getActions().size() == 0) System.out.println("List is empty!");
        for (MenuItem menuItem : getCurrentMenu()) {
            if (!menuItem.isAvailable())
                System.out.printf("%-4s%-3s%-22s%-6s%-5s%-15s%n", "[" + getCurrentMenu().indexOf(menuItem) + "]", " -", menuItem.getName(), getPriceFormat().format(menuItem.getPrice()), "zł", "- POSITION NOT AVAILABLE!");
            else
                System.out.printf("%-4s%-3s%-22s%-6s%-5s%-15s%n", "[" + getCurrentMenu().indexOf(menuItem) + "]", " -", menuItem.getName(), getPriceFormat().format(menuItem.getPrice()), "zł", menuItem.getDescription());
        }
    }


    public static void setInspectMode(boolean bool) {
        inspectMode = bool;
        if(getInspectMode()) System.out.println("Inspect mode will display additional messages like this one. Initial employees, menu, orders loaded. Turn restaurant work on to start processing.");
    }

    public static boolean getInspectMode(){return inspectMode;
    }


}


