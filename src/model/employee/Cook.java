package model.employee;

import java.util.ArrayList;
import java.util.List;

public class Cook extends Employee {
    private static final List<Cook> cooks = new ArrayList<>();
    public Cook(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);
        cooks.add(this);
    }

    public static List<Cook> getCooks() {
        return cooks;
    }
}
