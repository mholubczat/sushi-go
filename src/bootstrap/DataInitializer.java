package bootstrap;

import model.employee.Cook;

import static view.InputValidator.getPhone;
import static view.InputValidator.getString;

public class DataInitializer {
    public static void initialize(){
        new Cook("Carissa","Tillman","908293381");
        new Cook("Rashad","Flynn","312668082");
        new Cook("Briar","Haney","315279479");




    }
}
