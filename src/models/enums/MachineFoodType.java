package models.enums;

public enum MachineFoodType {
    PIZZA("Pizza"),
    SANDWICH("sandwich"),
    RICE("Rice"),
    STAKE("Stake");
    private  String food;

    MachineFoodType(String food) {
        this.food = food;
    }

    public String getFood() {
        return food;
    }
}
