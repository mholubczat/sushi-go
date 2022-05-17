package view;

public final class DisplayProfessions extends TerminalScreen {
    private static DisplayProfessions displayProfessions;

    private DisplayProfessions(String message) {
        super(message);
    }

    public static DisplayProfessions getProfessionMenu() {
        if (displayProfessions == null) {
            displayProfessions = new DisplayProfessions("Enter employee profession:");

            displayProfessions.addAction("Cook");
            displayProfessions.addAction("Waiter");
            displayProfessions.addAction("Delivery");
        }
        return displayProfessions;
    }

}
