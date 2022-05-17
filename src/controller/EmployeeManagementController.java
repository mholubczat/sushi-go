package controller;
import service.IEmployeeManagementService;

import static view.EmployeeManagement.getEmployeeManagement;
import static utils.InputValidator.getInt;
public final class EmployeeManagementController  {
    private final IEmployeeManagementService employeeManagementService;

    public EmployeeManagementController(IEmployeeManagementService employeeManagementService) {
        this.employeeManagementService = employeeManagementService;
    }

    public void invokeAction() {
        getEmployeeManagement().display();
        switch (getInt("")) {

            case 0 -> employeeManagementService.hireEmployee();
            case 1 -> employeeManagementService.dismissEmployee();
            case 2 -> employeeManagementService.employeeStatus();
            case 3 -> employeeManagementService.showEmployees();
            case 4 -> {
            }
            default -> {
                System.out.println("Please choose and option from 0 to " + (getEmployeeManagement().getActions().size() - 1));
                invokeAction();
            }
        }
    }
}
