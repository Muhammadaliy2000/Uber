package models;

public class Driver extends BaseModel{
    private String carNumber;
    private Boolean isFree;
    public Driver() {
        this.isFree = true;
        super.setActive(true);
    }

    public Driver(String name, String username, String password, String phoneNumber, Boolean isActive, Type type, double locX, double locY, String carNumber, Boolean isFree) {
        super(name, username, password, phoneNumber, isActive, type, locX, locY);
        this.carNumber = carNumber;
        this.isFree = isFree;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Boolean isFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }
}
