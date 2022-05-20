package model;

public class OnlineOrder extends Order {
    private final Address address;

    public OnlineOrder(Address address) {
        this.address = address;
    }
}
