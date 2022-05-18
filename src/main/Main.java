package main;
import bootstrap.DataInitializer;
import model.Order;

import static model.Order.getOrderPriorityQueue;

public class Main {

    public static void main(String[] args) {
        new DataInitializer().initialize();
        while(!(getOrderPriorityQueue().peek()==null)){
            System.out.println(getOrderPriorityQueue().poll());}


        new Terminal().run();
     //   new Kitchen().run();
//https://www.samouczekprogramisty.pl/watki-w-jezyku-java/
    }
}
