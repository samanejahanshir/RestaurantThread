package service;

import models.Cook;
import models.Customer;
import models.Machine;
import models.Order;
import models.enums.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Restaurant {
    public int customerCount;
    public final int capacity = 4;
    public List<Cook> cookList = new ArrayList<>();
    public List<Customer> customerList = new ArrayList<>();
    public List<Machine> machineList = new ArrayList<>();
    public List<Order> orderList = new ArrayList<>();
    public int idOrder = 1;
    public String state;


    public Restaurant() {
        state = RestaurantState.OPEN.getState();


    }

    //TODO
    public synchronized void acceptCustomer(Customer customer) throws InterruptedException {

        customer.setCustomerState(CustomerState.CUSTOMER_ENTERED.getState());
        customer.setName("customer " + customer.getCustomerName());
        customerCount++;
        customer.enter();

        System.out.println(Thread.currentThread().getName() + " entered");
        customer.setCustomerState(CustomerState.CUSTOMER_ENTERED.getState());



    }

    public synchronized Order selectAndAddOrder(Customer customer) throws InterruptedException {
        if (orderList.size() == cookList.size()) {
            wait();
        }
        System.out.println("1.pizza\n2.sandwich\n3.rice\n4.stake");
        Scanner scanner = new Scanner(System.in);
        String food = "";
        try {
            int number=Integer.parseInt(scanner.next());
            switch (number) {
                case 1:
                    food = MachineFoodType.PIZZA.getFood();
                    break;
                case 2:
                    food = MachineFoodType.SANDWICH.getFood();
                    break;
                case 3:
                    food = MachineFoodType.RICE.getFood();
                    break;
                case 4:
                    food = MachineFoodType.STAKE.getFood();
                    break;
                default:
                    throw new InputMismatchException("enter 1 - 4 !");

            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        Order order = new Order(idOrder++, food, OrderState.START.getState());
        orderList.add(order);
        customer.setCustomerState(CustomerState.CUSTOMER_PLACE_ORDER.getState());
        return order;


    }

    public synchronized void receiveOrder(Order order, Customer customer) throws InterruptedException {
        int index=searchOrderIndex(order.getOrderNum());

        if (!orderList.get(index).getState().equalsIgnoreCase(OrderState.FINISH.getState())) {
            wait();
        }else {
            System.out.println("order " + Thread.currentThread().getName() + " : ");
            System.out.println(Thread.currentThread().getName() + " receive order and  is eating it ... ");
            printLine();
            customer.setCustomerState(CustomerState.CUSTOMER_RECEIVE_ORDER.getState());
            wait(2000);
            int index2 = searchOrderIndex(order.getOrderNum());

            orderList.remove(index2);

        }
        notify();
    }

    public synchronized void leaveRestaurant(Customer customer) throws InterruptedException {
        if (customer.getCustomerState().equalsIgnoreCase(CustomerState.CUSTOMER_RECEIVE_ORDER.getState())) {
            customer.setCustomerState(CustomerState.CUSTOMER_LEAVING.getState());
            System.out.println(Thread.currentThread().getName() + " left restaurant");
        }
    }

    public synchronized Order getOrder(Cook cook) throws InterruptedException {
        if (orderList.isEmpty()) {
            try {
                System.out.println( Thread.currentThread().getName() + " is waiting for order ...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Order order = null;
        for (Order value : orderList) {
            if (value.getState().equalsIgnoreCase(OrderState.START.getState())) {
                order = value;
                order.setState(OrderState.IS_COOKING.getState());
                value.setState(OrderState.IS_COOKING.getState());
                System.out.println(Thread.currentThread().getName()+" get order : "+ order);
                printLine();
                cook.setCookState(CookState.COOK_RECEIVE_ORDER.getState());
                break;
            }

        }
        notify();
        return order;


    }

    public synchronized Machine findMachineAndCook(Cook cook) {
        Machine machine=null;
        for (Machine value : machineList) {
            if (cook.order != null) {
                if (value.getMachineFoodType().equalsIgnoreCase(cook.order.getFood())) {
                    if (value.getCountUse() < value.getCapability()) {
                        machine = value;
                        int count = value.getCountUse() + 1;
                        machine.setCountUse(count);
                        value.setCountUse(count);
                        value.setCook(cook);
                        return  machine;

                    } else {
                        while (value.getCountUse() == value.getCapability()) {
                            try {
                                //TODO
                                System.out.println("cook " + Thread.currentThread().getName() + " is waiting for one machine was free ... ");
                                wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }

        }
        return null;
    }
    public synchronized void getOrderToCustomer(Cook cook){
        if (cook.order != null) {
            if (cook.order.getState().equalsIgnoreCase(OrderState.COOKED.getState())) {
                cook.order.setState(OrderState.FINISH.getState());
                int index =searchOrderIndex(cook.order.getOrderNum());
               orderList.get(index).setState(OrderState.FINISH.getState());
                orderList.notify();
                System.out.println(Thread.currentThread().getName()+" delivered "+orderList.get(index));
                printLine();
                cook.setCookState( CookState.COOK_ENDING.getState());
                notify();
            }
        }

    }

    public synchronized int searchOrderIndex(int id) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderNum() == id) {
                return i;
            }
        }
        return -1;
    }
    public void printLine(){
        System.out.println("-----------------------------------------");
    }
}
