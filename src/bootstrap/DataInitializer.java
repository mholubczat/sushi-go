package bootstrap;

import model.LocalOrder;
import model.Order;
import model.employee.Cook;
import model.employee.Delivery;
import model.employee.Waiter;
import service.MenuManagementService;

import static model.LocalOrder.randomOrder;
import static model.Menu.getMenu;

public class DataInitializer {
    public static void initialize(){

        // employees
        new Cook("Carissa","Tillman","908293381");
        new Cook("Rashad","Flynn","312668082");
        new Cook("Briar","Haney","315279479");
        new Cook("Gisela","Kirk","035351756");
        new Cook("Erich","Weeks","277130665");
        new Waiter("Julie","Potter","422470431");
        new Waiter("Sybill","Mills","718722415");
        new Delivery("Rhiannon","Park","920667785");
        new Delivery("Griffin","Kirk","035351756");
        // menu
        new MenuManagementService().importMenu("BootstrapMenu");
        // orders
        for(int i = 0;i < (int)(Math.random()*10+5);i++) {
           LocalOrder randomLocal = randomOrder(i);
        }
    }

}
