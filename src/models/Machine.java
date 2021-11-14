package models;

import models.enums.MachineFoodType;

public class Machine {
    private  String name;
    private String machineFoodType;
    private  int capability;

    public Machine(String name, String machineFoodType, int capability) {
        this.name = name;
        this.machineFoodType = machineFoodType;
        this.capability = capability;
    }
}
