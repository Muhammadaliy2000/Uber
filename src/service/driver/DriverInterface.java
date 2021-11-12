package service.driver;

import models.driver.Driver;

import java.util.UUID;

public interface DriverInterface {
    boolean accept(Driver driver);

    boolean onTheWay(Driver driver);

    boolean finish(Driver driver);

    Driver getDriver(UUID driverId);
}
