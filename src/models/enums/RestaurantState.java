package models.enums;

public enum RestaurantState {
    OPEN("open"),
    FULL("full"),
    CLOSE("close");
    private String state;

    RestaurantState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
