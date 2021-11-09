package models;

import java.util.UUID;

public abstract class BaseModel {
    private UUID id;
    private String name;
    private String username;
    private String password;
    private String phoneNumber;
    private Boolean isActive;
    private Type type;
    private double locX;
    private double locY;

    public BaseModel() {
        this.id = UUID.randomUUID();
    }

    public BaseModel(String name, String username, String password, String phoneNumber, Boolean isActive, Type type, double locX, double locY) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.type = type;
        this.locX = locX;
        this.locY = locY;
    }

    public UUID getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public double getLocX() {
        return locX;
    }

    public void setLocX(double locX) {
        this.locX = locX;
    }

    public double getLocY() {
        return locY;
    }

    public void setLocY(double locY) {
        this.locY = locY;
    }
}
