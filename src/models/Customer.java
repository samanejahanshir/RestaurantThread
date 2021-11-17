package models;

import service.Restaurant;

public class Customer extends Thread {
    private  String customerName;
    private String customerState;
   // private  int orderNum;
   // private List<Order> orderList=new ArrayList<>();
   // private Cook cook;
    Restaurant restaurant;

    public Customer(String customerName,Restaurant restaurant) {
        this.customerName=customerName;
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
          Order order= restaurant.selectAndAddOrder(this);
           restaurant.receiveOrder(order,this);
           restaurant.leaveRestaurant(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       /* synchronized (orderList) {
            while (orderList.size() >= Restaurant.cookList.size()) {
                System.out.println("all cook is busy. please wait...");
                try {
                    orderList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int indexOrder = selectFood();
            if (orderList.get(indexOrder).getState().equalsIgnoreCase(OrderState.START.getState())) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            notify();*/

            //Order order=new Order(Restaurant.idOrder++,selectFood(), OrderState.START.getState());
            //   if(Restaurant.searchOrderIndex(order.getOrderNum())==-1) {
            //   orderList.add(order);
              /*  for(int i=0;i<orderList.stream().filter(tempOrder->tempOrder.getState().equalsIgnoreCase(OrderState.START.getState())).count();i++){
if(Restaurant.cookList.get(i).getCookState().equalsIgnoreCase(CookState.COOK_STARTING.getState())) {
    cook = Restaurant.cookList.get(i);
    cook.start();
    Restaurant.cookList.get(i).setCookState(CookState.COOK_RECEIVE_ORDER.getState());
}*/

            // }
//            try {
//                orderList.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

          /*  while (orderList.get(Restaurant.searchOrderIndex(order.getOrderNum())).getState().equalsIgnoreCase(OrderState.IS_COOKING.getState())) {

                //TODO
                System.out.println("customer " + this.customerName + " is waiting for receive food ...");
                try {
                    orderList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }*/

           /* if (orderList.get(Restaurant.searchOrderIndex(order.getOrderNum())).getState().equalsIgnoreCase(OrderState.FINISH.getState())) {
                try {
                    orderList.remove(Restaurant.searchOrderIndex(order.getOrderNum()));
                    this.customerState = CustomerState.CUSTOMER_RECEIVE_ORDER.getState();
                    orderList.notify();
                    System.out.println("customer " + this.customerName + " is eating food ...");
                    this.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Customer" + this.customerName + "is leaving restaurant ... ");
                this.customerState = CustomerState.CUSTOMER_LEAVING.getState();
                Restaurant.customerCount--;


            }
            try {
                cook.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
*/
        }
        // if(customerState.equalsIgnoreCase(CustomerState.CUSTOMER_RECEIVE_ORDER.getState())){


      //  }
      public synchronized void enter() throws InterruptedException {
          while (restaurant.customerCount > restaurant.capacity) {

              System.out.println("restaurant is full " + customerName + " is waiting");
              this.wait();
          }
      }


}
