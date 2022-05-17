package view;

import model.MenuItem;

import static model.Menu.getCurrentMenu;

public class DisplayMenu extends TerminalScreen {

    public DisplayMenu(String message) {
        super(message);

        for (MenuItem menuItem : getCurrentMenu()) {
            if (!menuItem.isAvailable())
                this.addAction("POSITION NOT AVAILABLE - " + (menuItem.getName() + "\t\t\t" + menuItem.getDescription() + "\t\t\t" + menuItem.getPrice() + "zł"));
            else
                this.addAction((menuItem.getName() + "\t\t\t" + menuItem.getDescription() + "\t\t\t" + menuItem.getPrice() + "zł"));
        }
    }

    //TODO   https://stackoverflow.com/questions/15215326/how-can-i-create-table-using-ascii-in-a-console

}


