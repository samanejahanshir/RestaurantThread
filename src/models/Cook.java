package models;

import models.enums.CookState;
import models.enums.OrderState;
import service.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Cook extends Thread{
    private String cookName;
   /* private List<Order> orderList;
    private  List<Machine> machineList;*/
    private String cookState;
    private   Restaurant restaurant;

    public Cook(String cookName, Restaurant restaurant) {
        this.cookName = cookName;
        this.restaurant = restaurant;
    }

    public   Order order=new Order();

    public String getCookState() {
        return cookState;
    }

    public void setCookState(String cookState) {
        this.cookState = cookState;
    }



    @Override
    public void run() {

        try {
           order= restaurant.getOrder(this);
           restaurant.findMachineAndCook(this);
           restaurant.getOrderToCustomer(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
