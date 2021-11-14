package models;

import models.enums.CookState;

import java.util.ArrayList;
import java.util.List;

public class Cook extends Thread{
    private String cookName;
    private List<Order> orderList=new ArrayList<>();
    private  List<Machine> machineList=new ArrayList<>();
    private String cookState;

    public Cook(String name, List<Order> orderList, List<Machine> machineList) {
        this.cookName = name;
        this.orderList = orderList;
        this.machineList = machineList;
        cookState= CookState.COOK_STARTING.getState();
    }
}
