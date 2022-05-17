package service;

public interface IMenuManagementService {

    void showMenu();

    void addMenuItem();

    void removeMenuItem();
    void makeMenuItemAvailable();

    void makeMenuItemNotAvailable();

    void exportMenu();

    void importMenu();
}

