package model;

import java.math.BigDecimal;

public class MenuItem {
    private String name;
    private String description;
    private boolean isAvailable;
    private final BigDecimal price;

    public MenuItem(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.isAvailable = true;
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
