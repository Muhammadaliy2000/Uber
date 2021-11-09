package service;

import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminImpl implements AdminService<Driver, Responce>, ResponseStatus{
    private Scanner sc = new Scanner(System.in);
    private List<BaseModel> list = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();

    public AdminImpl(List<BaseModel> list, List<Order> orderList) {
        this.list = list;
        this.orderList = orderList;
    }

    @Override
    public Responce addDriver(Driver driver) {
        boolean ans = true;
        ans = ans && driver.getUsername() != null;
        ans = ans && driver.getPassword() != null;
        ans = ans && driver.getName() != null;
        ans = ans && driver.getPhoneNumber() != null;
        ans = ans && driver.getType() != null;
        ans = ans && driver.isFree() != null;

        if(ans) {
            list.add(driver);
            return new Responce(true, driver);
        } else {
            return new Responce(false, driver);
        }
    }

    @Override
    public Responce deleteDriver(String req) {
        for (BaseModel base : list) {
            if(base instanceof  Driver) {
                if(base.getPhoneNumber().equals(req) || ((Driver) base).getCarNumber().equals(req)) {
                    base.setActive(false);
                    return new Responce(true, req);
                }
            }
        }

        return new Responce(false, req);
    }


    @Override
    public void getAllOrders() {

    }

    /*@Override
    public void getNewOrderList() {
        boolean exit = true;
        while (exit) {
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getStatus() == OrderStatus.NEW) {
                    System.out.println((i+1)+". ");
                }
            }
        }
    }*/

}
