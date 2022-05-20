package model.employee;

import model.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class Employee {
    protected final String firstName;
    protected final String lastName;
    protected final String phoneNumber;
    private static List<Employee> employees;

    public Employee(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        if (employees == null) employees = new ArrayList<>();
        employees.add(this);
    }

    public static List<Employee> getEmployees() {
        return employees;
    }

    public static BigDecimal getTip(Order order) {
        BigDecimal orderValue = order.getValue();
        long orderTime = ChronoUnit.MINUTES.between(order.getOrderTime(), LocalDateTime.now());
        if (orderTime >= 15) {
            // przedawniony podczas dostawy, lipa w ....
            if (!order.isDelayed()) order.setDelayed(true);
            return BigDecimal.ZERO;
        } else if (orderTime < 5) return orderValue.divide(BigDecimal.valueOf(10), 2, RoundingMode.CEILING);
        else
            return orderValue.multiply(BigDecimal.valueOf(14 - orderTime)).divide(BigDecimal.valueOf(100), 2, RoundingMode.CEILING);
    }

    @Override
    public String toString() {
        return "[" + employees.indexOf(this) + "] - " + getClass().getSimpleName() + " - " + firstName + " " + lastName + " tel. " + phoneNumber;
    }
}
