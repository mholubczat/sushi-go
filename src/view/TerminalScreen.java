package view;
import java.util.ArrayList;

public abstract class TerminalScreen {

    // Message will be displayed in terminal, followed by menu options to pick by pressing numpad keys.
    // Immutable child classes will be implemented as a Singletons
    final String message;
    final ArrayList<String> actions;

    public TerminalScreen(String message) {
        this.message = message;
        this.actions = new ArrayList<>();
    }

    public void display(){
        System.out.println(this.getMessage());
        if (getActions().size() == 0) System.out.println("List is empty!");
        for(String action : this.getActions()){
            System.out.println("["+this.getActions().indexOf(action) + "] - " + action);
        }
    }

    public void addAction(String action){
        actions.add(action);
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public String getMessage() {
        return message;
    }
}
