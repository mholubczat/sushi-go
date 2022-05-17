package view;

public final class EmployeeManagement extends TerminalEntry{
    private static EmployeeManagement employeeManagement;

    public EmployeeManagement(String message) {
        super(message);
    }

    public static EmployeeManagement getEmployeeManagement(){
        if (employeeManagement == null) {
            employeeManagement = new EmployeeManagement("Choose one of the following actions:");

            employeeManagement.addAction("Hire an employee");
            employeeManagement.addAction("Dismiss an employee");
            employeeManagement.addAction("Display employee status");
            employeeManagement.addAction("Show all employees");
            employeeManagement.addAction("Back to previous menu");
        }
        return employeeManagement;
    }
}
