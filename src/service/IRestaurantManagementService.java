package service;

public interface IRestaurantManagementService {
    void startRestaurantWork();

    void startReceivingOnlineOrders();

    void stopRestaurantWork();

    void stopReceivingOnlineOrders();
}
