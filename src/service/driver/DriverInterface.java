package service.driver;

import models.driver.Driver;

public interface DriverInterface {
    boolean accept(Driver driver);

    boolean onTheWay(Driver driver);

    boolean finish(Driver driver);
}
