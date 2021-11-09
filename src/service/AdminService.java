package service;

import java.util.List;

public interface AdminService<T, R> {
    Object addDriver(T t);
    Object deleteDriver(String s);
    void getAllOrders();
    //void getOrderList();
    //void getNewOrderList();
}
