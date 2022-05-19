package model.employee;

import model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static model.Order.getFinishedOrders;

public class Deliverer extends Employee{
    private final static ArrayList<Deliverer> delivererList = new ArrayList<>();
    public Deliverer(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);
        delivererList.add(this);
    }

    public static ArrayList<Deliverer> getDelivererList(){return delivererList;}

    @Override
    public void execute(Runnable command) {
       // final Runnable asd = (Map.Entry<Integer, Order>) -> shieeet(Map.Entry<Integer, Order>));
    }

    public void shieeet(Map.Entry<Integer, Order> entry) {
        System.out.println("shiet");
    }
}
