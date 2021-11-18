package view;

import models.Cook;
import models.Customer;
import models.Machine;
import models.enums.CookState;
import models.enums.MachineFoodType;
import service.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Cook> cookList = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();
        List<Machine> machineList = new ArrayList<>();
        Restaurant restaurant = new Restaurant();
        Machine cookPizza = new Machine("cookPizza", MachineFoodType.PIZZA.getFood(), 3, restaurant);
        cookPizza.setName("cookPizza");
        Machine cookSandwich = new Machine("CookSandwich", MachineFoodType.SANDWICH.getFood(), 2, restaurant);
        cookSandwich.setName("CookSandwich");
        Machine cookRice = new Machine("CookRice", MachineFoodType.RICE.getFood(), 2, restaurant);
        cookRice.setName("CookRice");
        Machine cookStake = new Machine("CookStake", MachineFoodType.STAKE.getFood(), 3, restaurant);
        cookStake.setName("CookStake");
        machineList = List.of(cookPizza, cookSandwich, cookRice, cookStake);

        Cook sahar = new Cook("sahar", restaurant);
        sahar.setName("Cook sahar");
        sahar.setCookState(CookState.COOK_STARTING.getState());
        Cook ali = new Cook("ali", restaurant);
        ali.setName("Cook ali");
        ali.setCookState(CookState.COOK_STARTING.getState());

        Cook hadi = new Cook("hadi", restaurant);
        hadi.setName("Cook hadi");
        hadi.setCookState(CookState.COOK_STARTING.getState());

        Cook zahra = new Cook("zahra", restaurant);
        zahra.setName("Cook zahra");
        zahra.setCookState(CookState.COOK_STARTING.getState());

        cookList = List.of(sahar, ali, hadi, zahra);
        Customer sara = new Customer("sara", restaurant);
        sara.setName("Customer sara");
        Customer mahdi = new Customer("mahdi", restaurant);
        mahdi.setName("Customer mahdi");
        Customer fateme = new Customer("fateme", restaurant);
        fateme.setName("Customer fateme");
        Customer nader = new Customer("nader", restaurant);
        nader.setName("Customer nader");
        // Customer hamid = new Customer("hamid",restaurant);
        // hamid.setName("Customer hamid");
        customerList = List.of(sara, mahdi, fateme, nader);
        // Customer samane = new Customer(restaurant);
        //  Customer farhad = new Customer(restaurant);
        //  Customer reza = new Customer(restaurant);
        restaurant.customerList = customerList;
        restaurant.cookList = cookList;
        restaurant.machineList = machineList;
        List<Thread> threads = new ArrayList<>();
        // threads.addAll(machineList);
        //threads.addAll(cookList);
        threads.addAll(customerList);
        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


    }
}
