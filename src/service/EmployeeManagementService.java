package service;

import model.employee.Cook;
import model.employee.Deliverer;
import model.employee.Employee;
import model.employee.Waiter;

import static model.employee.Employee.getEmployees;
import static utils.InputValidator.*;
import static view.DisplayProfessions.getProfessionMenu;

public class EmployeeManagementService implements IEmployeeManagementService {

    public EmployeeManagementService() {
    }

    @Override
    public void hireEmployee() {
        getProfessionMenu().display();
        switch (getInt("")) {
            case 0 ->
                    new Cook(getString("Enter first name"), getString("Enter last name"), getPhone("Enter phone number"));
            case 1 ->
                    new Waiter(getString("Enter first name"), getString("Enter last name"), getPhone("Enter phone number"));
            case 2 ->
                    new Deliverer(getString("Enter first name"), getString("Enter last name"), getPhone("Enter phone number"));
            default -> {
                System.out.println("Enter number 0-2");
                hireEmployee();
            }
        }
    }

    @Override
    public void dismissEmployee() {
        showEmployees();
        int employeeToDismiss = getInt("Enter employee number");
        Class<? extends Employee> profession = getEmployees().get(employeeToDismiss).getClass();

        getEmployees().remove(employeeToDismiss);
        if (getEmployees().stream().noneMatch(employee -> employee.getClass().equals(profession)))
            System.out.println("To żeś kurwa narobił"); //TODO no no
    }

    @Override
    public void employeeStatus() {
        showEmployees();
        int employeeToDisplay = getInt("Enter employee number");
        System.out.println(getEmployees().get(employeeToDisplay));
    }

    @Override
    public void showEmployees() {
        getEmployees().forEach(System.out::println);
    }
}
