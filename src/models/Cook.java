package models;

import java.util.ArrayList;
import java.util.List;

public class Cook {
    private String name;
    private List<Order> orderList=new ArrayList<>();
    private  List<Machine> machineList=new ArrayList<>();

    public Cook(String name, List<Order> orderList, List<Machine> machineList) {
        this.name = name;
        this.orderList = orderList;
        this.machineList = machineList;
    }
}
