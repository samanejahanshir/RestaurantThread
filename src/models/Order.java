package models;

public class Order {
    private  int orderNum;
    private  String food;
    private String state;

    public Order(int orderNum, String food,String state) {
        this.orderNum = orderNum;
        this.food = food;
        this.state=state;
    }

    public Order() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNum=" + orderNum +
                ", food='" + food + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
