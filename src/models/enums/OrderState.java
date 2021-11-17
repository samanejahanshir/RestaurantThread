package models.enums;

public enum OrderState {
    START("start"),
    IS_COOKING("isCooking"),
    COOKED("cooked"),
    FINISH("finish");
    private String state;

    OrderState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
