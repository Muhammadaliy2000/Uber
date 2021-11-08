package models;

public class Driver extends BaseModel{
    private String carNumber;
    private Boolean isFree;

    public Driver(String name, String username, String password, Boolean isActive, Type type, String carNumber, Boolean isFree) {
        super(name, username, password, isActive, type);
        this.carNumber = carNumber;
        this.isFree = isFree;
    }

    public Driver() {
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Boolean getStatus() {
        return isFree;
    }

    public void setStatus(Boolean isFree) {
        this.isFree = isFree;
    }
}
