package main;
import bootstrap.DataInitializer;

public class Main {

    public static void main(String[] args) {

        new DataInitializer().initialize();
        new Terminal().run();
     //   new Kitchen().run();

    }
}
