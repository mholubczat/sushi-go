package bootstrap;

import model.Address;
import model.LocalOrder;
import model.OnlineOrder;
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
        for (int i = 0; i < (int) (Math.random() * 10 + 5); i++) {
            Order.randomOrder(new LocalOrder(i));
        }
        //few addresses

        Address[] addresses = {
                new Address("Rue Strasbourg", "22", "1", "H9G 1R9", "Dollard-des-Ormeaux"),
                new Address("Betnor Avenue", "3", "", "01-001"),
                new Address("Ickworth Crescent", "153a", "2", "DY6 8DA", "Kingswinford"),
                new Address("Dalton Warren", "144", "LL31 9HG", "Llandudno Junction"),
                new Address("Matlock Copse", "44f", "HD8 8NR", "Huddersfield"),
                new Address("Rowland Birches", "1", "A1E 5Z7", "St John's"),
                new Address("Elliott Place", "15/57", "31", "L8H 1C3", "Hamilton"),
                new Address("Rowland Cottages", "14", "2", "L8H 1C3", "Hamilton"),
                new Address("Cresswell Place", "2", "11", "EN6 5AJ", "Potters Bar"),
                new Address("Bowness Covert", "185", "24c", "T0B 0S0", "Bruderheim"),
                new Address("Piper Gate", "44/8b", "13", "NN15 7HH", "Brackley"),
                new Address("Jordan Moor", "552", "24", "HD8 8NR", "Huddersfield"),
                new Address("Dane Passage", "2112", "4", "KA4 8HT", "Galston"),
                new Address("Balquharn Way", "1124", "DY6 8DA", "Kingswinford"),
                new Address("Boston Mount", "3", "T9N", "Bonnyville")
        };

        for (int i = 0; i < (int) (Math.random() * 10 + 5); i++) {
            Order.randomOrder(new OnlineOrder(addresses[i]));
        }
    }

}
