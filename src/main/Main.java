package main;
import bootstrap.DataInitializer;
import model.Order;

import static model.Order.getOrderPriorityQueue;

public class Main {

    public static void main(String[] args) {

        new DataInitializer().initialize();

        System.out.println(getOrderPriorityQueue().peek());
       Order o1 = getOrderPriorityQueue().poll();
        System.out.println(getOrderPriorityQueue().peek());
        Order o2= getOrderPriorityQueue().poll();
        System.out.println(getOrderPriorityQueue().peek());
        Order o3= getOrderPriorityQueue().poll();
        System.out.println(getOrderPriorityQueue().peek());
        Order o4= getOrderPriorityQueue().poll();
        System.out.println(o1.compareTo(o2));
        System.out.println(o2.compareTo(o3));
        System.out.println(o3.compareTo(o4));
        System.out.println(o4.compareTo(o1));
        System.out.println(getOrderPriorityQueue().poll());
        System.out.println(getOrderPriorityQueue().poll());

        System.out.println(getOrderPriorityQueue().poll());

        System.out.println(getOrderPriorityQueue().poll());

        System.out.println(getOrderPriorityQueue().poll());

        System.out.println(getOrderPriorityQueue().peek());
Order o5 =         getOrderPriorityQueue().poll();
        System.out.println(o4.compareTo(o5));


        new Terminal().run();
     //   new Kitchen().run();
//https://www.samouczekprogramisty.pl/watki-w-jezyku-java/
    }
}
