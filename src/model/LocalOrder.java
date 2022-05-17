package model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class LocalOrder extends Order{
    private int tableNumber;


    public LocalOrder(int tableNumber) {
        super();
        this.tableNumber = tableNumber;
    }


}
