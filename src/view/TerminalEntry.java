package view;

import java.util.ArrayList;

import static view.MainMenu.getMainMenu;

public abstract class TerminalEntry {

    // Each implementing class is a Singleton
    // Message will be displayed in terminal, followed by menu options to pick by pressing numpad keys.
    String message;
    ArrayList<String> actions;

    public TerminalEntry(String message) {
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

    public void setActions(ArrayList<String> actions) {
        this.actions = actions;
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public String getMessage() {
        return message;
    }
}
