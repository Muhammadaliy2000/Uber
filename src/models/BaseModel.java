package models;

import java.util.UUID;

public abstract class BaseModel {
    protected UUID id;
    protected String name;
    protected String username;
    protected String password;
    protected String phoneNumber;
    protected Boolean isActive;
    protected int locX;
    protected int locY;
    protected char symbol;

    public BaseModel() {
        this.id = UUID.randomUUID();
    }
//symbol --
    public BaseModel(String name, String username, String password, String phoneNumber, int locX, int locY,char symbol) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isActive = true;
        this.locX = locX;
        this.locY = locY;
        this.symbol  = symbol;
    }

    public UUID getId() {
        return id;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
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

    public int getLocX() {
        return locX;
    }

    public void setLocX(int locX) {
        this.locX = locX;
    }

    public int getLocY() {
        return locY;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }


}
