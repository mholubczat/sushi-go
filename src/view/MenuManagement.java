package view;

public final class MenuManagement extends TerminalEntry{
    private static MenuManagement menuManagement;

    public MenuManagement(String message) {
        super(message);
    }

    public static MenuManagement getMenuManagement(){
        if (menuManagement == null) {
            menuManagement = new MenuManagement("Choose one of the following actions:");
            menuManagement.addAction("Show current menu");
            menuManagement.addAction("Add menu item");
            menuManagement.addAction("Remove menu item");
            menuManagement.addAction("Make menu item available");
            menuManagement.addAction("Make menu item not available");
            menuManagement.addAction("Export menu to .csv file");
            menuManagement.addAction("Import menu from .csv file");
            menuManagement.addAction("Back to previous menu");
        }
        return menuManagement;
    }
}