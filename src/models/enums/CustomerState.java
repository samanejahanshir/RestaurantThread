package models.enums;

public enum CustomerState {
    CUSTOMER_STARTING("CustomerStarting"),
    CUSTOMER_ENTERED("CustomerEntered"),
    CUSTOMER_PLACE_ORDER("CustomerPlaceOrder"),
    CUSTOMER_RECEIVE_ORDER("CustomerReceiveOrder"),
    CUSTOMER_LEAVING("CustomerLeaving");
    private  String state;

    CustomerState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
