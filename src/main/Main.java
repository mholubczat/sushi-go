package main;

import static bootstrap.DataInitializer.initialize;

public class Main {
    public static void main(String[] args) {
        initialize();
        new Terminal().start();
        new Kitchen().start();
    }
}
