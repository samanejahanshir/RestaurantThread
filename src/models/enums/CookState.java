package models.enums;

public enum CookState {
    COOK_STARTING("CookStarting"),
    COOK_RECEIVE_ORDER("CookReceiveOrder"),
    COOK_START_FOOD("CookStartFood"),
    COOK_FINISHED_FOOD("CookFinishedFood"),
    COOK_COMPLETE_ORDER("CookCompleteOrder"),
    COOK_ENDING("CookEnding");
    private  String state;

    CookState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
