package model;

public class Address {
    final String street;
    final String buildingNumber;
    String flatNumber;
    final String postalCode;
    final String city;

    public Address(String street, String buildingNumber, String postalCode, String city) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    public Address(String street, String buildingNumber, String flatNumber, String postalCode, String city) {
        this(street, buildingNumber, postalCode, city);
        this.flatNumber = flatNumber;
    }
}
