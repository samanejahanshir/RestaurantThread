package models;

import models.enums.CookState;
import service.Restaurant;

public class Customer extends Thread {
    private String customerName;
    private String customerState;
    // private  int orderNum;
    // private List<Order> orderList=new ArrayList<>();
    // private Cook cook;
    Restaurant restaurant;

    public Customer(String customerName, Restaurant restaurant) {
        this.customerName = customerName;
        this.restaurant = restaurant;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String state) {
        this.customerState = state;
    }

    @Override
    public void run() {
        try {
            restaurant.acceptCustomer(this);
            Order order = restaurant.selectAndAddOrder(this);
            Cook cook = restaurant.findCookToGetOrder();
            synchronized (cook) {
                if (cook != null) {
                    cook.start();
                }
            }
            cook.join();
            restaurant.receiveOrder(order, this);
            restaurant.leaveRestaurant(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public synchronized void enter() throws InterruptedException {

        while (restaurant.customerCount > restaurant.capacity) {

            System.out.println("restaurant is full " + customerName + " is waiting");
            this.wait();
        }

    }


}
