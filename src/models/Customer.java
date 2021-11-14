package models;

import models.enums.CustomerState;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Thread {
    private  String customerName;
    private String customerState;
    private  int orderNum;
    private List<Order> orderList=new ArrayList<>();

    public Customer(String name, List<Order> orderList) {
        this.customerName = name;
        this.orderList = orderList;
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
}
