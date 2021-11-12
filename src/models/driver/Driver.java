package models.driver;

import models.BaseModel;
import models.visual.GameScreen;

public class Driver extends BaseModel {
    private String carNumber;
    private Boolean isFree;

    public Driver() {
        this.isFree = true;
        super.setActive(true);
    }

    public Driver(String name, String username, String password, String phoneNumber, int locX, int locY, String carNumber) {
        super(name, username, password, phoneNumber, locX, locY,'D');
        this.carNumber = carNumber;
        this.isFree = true;
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

    public void moveLeft(GameScreen screen, Driver driver) {
        driver.setLocX(driver.getLocX() - 1);
        screen.setObjectOnLocation(driver, driver.getLocX(), driver.getLocY());
        screen.ClearScreenLocation(driver.getLocX() + 1, driver.getLocY());
    }


    public void moveRight(GameScreen screen, Driver driver) {
        driver.setLocX(driver.getLocX() + 1);
        screen.setObjectOnLocation(driver, driver.getLocX(), driver.getLocY());
        screen.ClearScreenLocation(driver.getLocX() - 1, driver.getLocY());
    }

    public void moveUp(GameScreen screen, Driver driver) {
        driver.setLocY(driver.getLocY() - 1);
        screen.setObjectOnLocation(driver, driver.getLocX(), driver.getLocY());
        screen.ClearScreenLocation(driver.getLocX(), driver.getLocY() + 1);
    }

    public void moveDown(GameScreen screen, Driver driver) {
        driver.setLocY(driver.getLocY() + 1);
        screen.setObjectOnLocation(driver, driver.getLocX(), driver.getLocY());
        screen.ClearScreenLocation(driver.getLocX(), driver.getLocY() - 1);
    }

    @Override
    public String toString() {
        return  " | name='" + name + '\'' +
                " | username='" + username + '\'' +
                " | password='" + password + '\'' +
                " | phoneNumber='" + phoneNumber + '\'' +
                " | isActive=" + isActive +
                " | locX=" + locX +
                " | locY=" + locY +
                " | carNumber='" + carNumber + '\'' +
                " | isFree=" + isFree;
    }
}
