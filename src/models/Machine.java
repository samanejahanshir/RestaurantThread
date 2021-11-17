package models;

import models.enums.MachineFoodType;
import models.enums.OrderState;
import service.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Machine extends Thread {
    private String machineName;
    private String machineFoodType;
    private int capability;
    private int countUse;
    private Cook cook;
    private Restaurant restaurant;
   // private List<Order> orderList = new ArrayList<>();


    public Machine(String machineName, String machineFoodType, int capability, Restaurant restaurant) {
        this.machineName = machineName;
        this.machineFoodType = machineFoodType;
        this.capability = capability;
        this.restaurant = restaurant;
    }

    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public int getCountUse() {
        return countUse;
    }

    public void setCountUse(int countUse) {
        this.countUse = countUse;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineFoodType() {
        return machineFoodType;
    }

    public void setMachineFoodType(String machineFoodType) {
        this.machineFoodType = machineFoodType;
    }

    public int getCapability() {
        return capability;
    }

    public void setCapability(int capability) {
        this.capability = capability;
    }

    @Override
    public void run() {
            synchronized (this) {
                while (restaurant.orderList.isEmpty()) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (this.countUse == this.capability) {
                    try {
                        System.out.println(machineName + " is full please wait ...");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (!restaurant.orderList.isEmpty()) {
                    try {
                        this.wait(2000);
                        //orderList.get(Restaurant.searchOrderIndex(cook.order.getOrderNum())).setState(OrderState.COOKED.getState());
                        cook.order.setState(OrderState.COOKED.getState());

                        this.setCountUse(this.getCountUse() - 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
                notifyAll();


            }
        //}
    }
}
