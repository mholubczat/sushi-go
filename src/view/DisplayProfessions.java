package view;

public final class DisplayProfessions extends TerminalScreen {
    private static DisplayProfessions displayProfessions;

    private DisplayProfessions() {
        super("Enter employee profession:");
    }

    public static DisplayProfessions getProfessionMenu() {
        if (displayProfessions == null) {
            displayProfessions = new DisplayProfessions();

            displayProfessions.addAction("Cook");
            displayProfessions.addAction("Waiter");
            displayProfessions.addAction("Delivery");
        }
        return displayProfessions;
    }

}
