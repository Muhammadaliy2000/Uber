package service;

import models.BaseModel;
import models.Order;
import models.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminImpl implements AdminService{
    private Scanner sc = new Scanner(System.in);
    private List<BaseModel> list = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();

    public AdminImpl(List<BaseModel> list, List<Order> orderList) {
        this.list = list;
        this.orderList = orderList;
    }

    @Override
    public void getNewOrderList() {
        boolean exit = true;
        while (exit) {
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getStatus() == OrderStatus.NEW) {
                    System.out.println((i+1)+". ");
                }
            }
        }
    }

    @Override
    public void getOrderList() {
        def main
    }

}
