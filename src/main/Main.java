package main;

import bootstrap.DataInitializer;
import threads.Deliveries;
import threads.IncomingOnlineOrders;
import threads.Kitchen;
import threads.Terminal;

public class Main {

    public static void main(String[] args) {
        new DataInitializer().initialize();
        new Terminal().start();
        new Kitchen().start();
        new IncomingOnlineOrders().start();
        new Deliveries().start();
//https://www.samouczekprogramisty.pl/watki-w-jezyku-java/
    }
}


