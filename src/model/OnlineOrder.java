package model;

public class OnlineOrder extends Order {
    private Address address;

    public OnlineOrder(Address address) {
        this.address = address;
    }
}
