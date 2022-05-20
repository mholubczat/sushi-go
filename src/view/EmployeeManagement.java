package view;

public final class EmployeeManagement extends TerminalScreen {
    private static EmployeeManagement employeeManagement;

    private EmployeeManagement() {
        super("Choose one of the following actions:");
    }

    public static EmployeeManagement getEmployeeManagement() {
        if (employeeManagement == null) {
            employeeManagement = new EmployeeManagement();

            employeeManagement.addAction("Hire an employee");
            employeeManagement.addAction("Dismiss an employee");
            employeeManagement.addAction("Display employee status");
            employeeManagement.addAction("Show all employees");
            employeeManagement.addAction("Back to previous menu");
        }
        return employeeManagement;
    }
}
