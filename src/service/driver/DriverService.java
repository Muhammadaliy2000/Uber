package service.driver;

import models.driver.Driver;
import models.order.OrderStatus;
import service.BaseInterface;
import service.order.OrderService;
import models.responce.Responce;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DriverService implements DriverInterface, BaseInterface<Driver> {
    public static final List<Driver> driverList = new ArrayList<>();

    @Override
    public Responce add(Driver driver) {
        boolean ans = true;
        ans = ans && driver.getUsername() != null;
        ans = ans && driver.getPassword() != null;
        ans = ans && driver.getName() != null;
        ans = ans && driver.getPhoneNumber() != null;

        if(ans) {
            driverList.add(driver);
            return new Responce(true, driver);
        } else {
            return new Responce(false, driver);
        }
    }

    @Override
    public Responce delete(UUID driverId) {
        for (int i = 0; i < driverList.size(); i++) {
            if(driverList.get(i).getId() == driverId) {
                driverList.get(i).setActive(false);
                return new Responce(true, driverId);
            }
        }

        return new Responce(false, driverId);
    }

    @Override
    public List<Driver> getList() {
        return driverList;
    }

    @Override
    public boolean edit(UUID driverId) {
        for (int i = 0; i < driverList.size(); i++) {
            if(driverList.get(i).getId() == driverId && !driverList.get(i).isActive()) {
                driverList.get(i).setActive(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean accept(Driver driver) {
        for (int i = 0; i < OrderService.orderList.size(); i++) {
            if(OrderService.orderList.get(i).getDriverId() == driver.getId() && OrderService.orderList.get(i).getStatus() == OrderStatus.NEW) {
                OrderService.orderList.get(i).setStatus(OrderStatus.ACCEPT);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onTheWay(Driver driver) {
        for (int i = 0; i < OrderService.orderList.size(); i++) {
            if(OrderService.orderList.get(i).getDriverId() == driver.getId() && OrderService.orderList.get(i).getStatus() == OrderStatus.ACCEPT) {
                OrderService.orderList.get(i).setStatus(OrderStatus.ON_THE_WAY);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean finish(Driver driver) {
        for (int i = 0; i < OrderService.orderList.size(); i++) {
            if(OrderService.orderList.get(i).getDriverId() == driver.getId() && OrderService.orderList.get(i).getStatus() == OrderStatus.ON_THE_WAY) {
                OrderService.orderList.get(i).setStatus(OrderStatus.FINISH);
                return true;
            }
        }

        return false;
    }
}
