package model.employee;

import java.util.ArrayList;
import java.util.List;

public class Waiter extends Employee{
    private final static ArrayList<Waiter> waiterList = new ArrayList<>();
    public Waiter(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);
    }

    public static ArrayList<Waiter> getWaiterList(){return waiterList;}

    @Override
    public void execute(Runnable command) {
        final Runnable asd = () -> System.out.println("asd");
    }
}
