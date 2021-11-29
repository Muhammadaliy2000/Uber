package service.driver;

import models.driver.Driver;

//import java.sql.Driver;
import java.util.UUID;

public interface DriverInterface {
    boolean accept(UUID id);

    boolean onTheWay(UUID id);

    boolean finish(UUID id);

    Driver getDriver(UUID driverId);
}
