package view;

public final class MainMenu extends TerminalScreen {
    private static MainMenu mainMenu;

    private MainMenu(String message) {
        super(message);
    }

    public static MainMenu getMainMenu() {
        if (mainMenu == null) {
            mainMenu = new MainMenu("Choose one of the following actions:");

            mainMenu.addAction("Orders");
            mainMenu.addAction("Restaurant management");
            mainMenu.addAction("Menu management");
            mainMenu.addAction("Employee management");
        }
        return mainMenu;
    }

}
