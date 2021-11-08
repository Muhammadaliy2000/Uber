package models;

public class Order {
    private User user;
    private Driver driver;
    private OrderStatus status;
    private String addressFrom;//finalX
    private String addressTo;// finalY

    public Order(User user, Driver driver, OrderStatus status, String addressFrom, String addressTo) {
        this.user = user;
        this.driver = driver;
        this.status = status;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
    }

    public Order() {
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

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }
}
