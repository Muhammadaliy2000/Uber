package models;

public class Order {
    private User user;
    private Driver driver;
    private OrderStatus status;
//    private String addressFrom;
//    private String addressTo;
    private double finalX;
    private double finalY;

    public Order() {
    }

    public Order(User user, Driver driver, OrderStatus status, double finalX, double finalY) {
        this.user = user;
        this.driver = driver;
        this.status = status;
        this.finalX = finalX;
        this.finalY = finalY;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getFinalX() {
        return finalX;
    }

    public void setFinalX(double finalX) {
        this.finalX = finalX;
    }

    public double getFinalY() {
        return finalY;
    }

    public void setFinalY(double finalY) {
        this.finalY = finalY;
    }
}
