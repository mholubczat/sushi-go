package model;

import static model.Menu.getMenu;

public class LocalOrder extends Order{
    private int tableNumber;


    public LocalOrder(int tableNumber) {
        super();
        this.tableNumber = tableNumber;
    }

    public static LocalOrder randomOrder(int tableNumber){
        LocalOrder randomOrder = new LocalOrder(tableNumber);
        for(int i = 0; i < (int)(Math.random()*5+1); i++){
            randomOrder.addOrderItem(getMenu().getCurrentMenu().get((int)(Math.random()*getMenu().getCurrentMenu().size())),((int)(Math.random()*2)+1));
        }
        return randomOrder;
    }
}
