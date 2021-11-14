package models;

import models.enums.CustomerState;

import java.util.ArrayList;
import java.util.List;

public class Customer  {
    private  String name;
    private CustomerState state;
    private  int orderNum;
    private List<Order> orderList=new ArrayList<>();

    public Customer(String name, List<Order> orderList) {
        this.name = name;
        this.orderList = orderList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerState getState() {
        return state;
    }

    public void setState(CustomerState state) {
        this.state = state;
    }
}
