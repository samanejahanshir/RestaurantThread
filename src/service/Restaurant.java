package service;

import models.Cook;
import models.Customer;
import models.Machine;
import models.Order;
import models.enums.CustomerState;
import models.enums.MachineFoodType;
import models.enums.RestaurantState;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    public static int customerCount;
    public static final int capacity = 10;
    public List<Cook> cookList = new ArrayList<>();
    public List<Customer> customerList = new ArrayList<>();
    public List<Machine> machineList = new ArrayList<>();
    public List<Order> orderList = new ArrayList<>();
    public String state;

    public Restaurant() {
        state = RestaurantState.OPEN.getState();
        machineList = List.of(new Machine("cookPizza", MachineFoodType.PIZZA.getFood(), 3)
                , new Machine("CookSandwich", MachineFoodType.SANDWICH.getFood(), 2)
                , new Machine("CookRice", MachineFoodType.RICE.getFood(), 2)
                , new Machine("CookStake", MachineFoodType.STAKE.getFood(), 3));
        cookList = List.of(new Cook("sahar", orderList, machineList)
                , new Cook("ali", orderList, machineList)
                , new Cook("hadi", orderList, machineList)
                , new Cook("zahra", orderList, machineList));

    }
    public void acceptCustomer(Customer customer){
        synchronized (state){
            while (state.equalsIgnoreCase(RestaurantState.FULL.getState())){
                try {
                    System.out.println("restaurant is full . please wait ...");
                    state.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            customer.setCustomerState(CustomerState.CUSTOMER_ENTERED.getState());
            customerList.add(customer);
            customerCount++;

            if (customerCount==capacity){
                state=RestaurantState.FULL.getState();
            }
        }
    }
}
