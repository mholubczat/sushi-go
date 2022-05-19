package main;

import bootstrap.DataInitializer;
import threads.Terminal;

public class Main {
    public static void main(String[] args) {
        new DataInitializer().initialize();
        new Terminal().start();
//https://www.samouczekprogramisty.pl/watki-w-jezyku-java/
    }
}


