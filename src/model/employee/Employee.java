package model.employee;

import java.util.ArrayList;
import java.util.List;

public abstract class Employee {
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;

    private static List<Employee> employees;

    public Employee(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        if (employees == null) employees = new ArrayList<>();
        employees.add(this);
    }

    public void fireEmployee(int number) {
        Class<? extends Employee> profession = employees.get(number).getClass();

        employees.remove(number);
        if (employees.stream().noneMatch(employee -> employee.getClass().equals(profession)))
            System.out.println("To żeś kurwa narobił"); //TODO obsługa wyjątku
    }

    public static List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "[" + employees.indexOf(this) + "] - " + getClass().getSimpleName() + " - " + firstName + " " + lastName + " tel. " + phoneNumber;
    }
}
