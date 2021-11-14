package models.enums;

public enum MachineState {
    MACHINE_STARTING("MachineStarting"),
    MACHINE_STARTING_FOOD("MachineStartingFood"),
    MACHINE_DONE_FOOD("MachineDoneFood");
    private  String state;

    MachineState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
