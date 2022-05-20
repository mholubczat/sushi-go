package view;

public final class MainMenu extends TerminalScreen {
    private static MainMenu mainMenu;

    private MainMenu() {
        super("Choose one of the following actions:");
    }

    public static MainMenu getMainMenu() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();

            mainMenu.addAction("Orders");
            mainMenu.addAction("Restaurant management");
            mainMenu.addAction("Menu management");
            mainMenu.addAction("Employee management");
            mainMenu.addAction("Simulation speed");
            mainMenu.addAction("Inspect mode");
        }
        return mainMenu;
    }

}
