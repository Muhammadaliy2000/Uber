package models.order;

import models.driver.Driver;
import models.user.User;
import models.visual.GameScreen;

import java.util.UUID;

public class Order {
    private UUID id;
    private UUID userId;
    private UUID driverId;
    private OrderStatus status;
    private int finalX;
    private int finalY;
    private char symbol;

    public Order() {
        this.id = UUID.randomUUID();
        this.status = OrderStatus.NEW;
        this.symbol = 'F';
    }

    public Order(UUID userId, UUID driverId, int finalX, int finalY) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.driverId = driverId;
        this.status = OrderStatus.NEW;
        this.finalX = finalX;
        this.finalY = finalY;
        this.symbol = 'F';
    }

    public void addLocation(GameScreen screen, Order order){
//        int x = (int) (Math.random() * (screen.getScreenWidth() - 1));
//        int y = (int) (Math.random() * (screen.getScreenHeight() - 1));
        screen.setOrderOnLocation(order, order.getFinalX(), order.getFinalY());
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getDriverId() {
        return driverId;
    }

    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getFinalX() {
        return finalX;
    }

    public void setFinalX(int finalX) {
        this.finalX = finalX;
    }

    public int getFinalY() {
        return finalY;
    }

    public void setFinalY(int finalY) {
        this.finalY = finalY;
    }

    @Override
    public String toString() {
        return  "\nstatus: " + status +
                "\nfinalX: " + finalX +
                "\nfinalY: " + finalY;
    }
}
