package service.order;

import models.order.Order;
import models.order.OrderStatus;
import models.responce.Responce;
import service.BaseInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService implements OrderInterface, BaseInterface<Order> {
    public static final List<Order> orderList = new ArrayList<>();

    @Override
    public Responce add(Order order) {
        boolean ans = true;
        ans = ans && order.getUserId() != null;

        if(ans) {
            orderList.add(order);
            return new Responce(true, order);
        } else {
            return new Responce(false, order);
        }
    }

    @Override
    public boolean cancelOrder(UUID orderId) {
        for (int i = 0; i < orderList.size(); i++) {
            if(orderList.get(i).getId() == orderId) {
                orderList.get(i).setStatus(OrderStatus.CANCEL);
                return true;
            }
        }

        return false;
    }

//    @Override
//    public Responce showOrderProcess(UUID orderId) {
//        for (int i = 0; i < orderList.size(); i++) {
//            if(orderList.get(i).getId() == orderId) {
//                return new Responce(true, orderList.get(i));
//            }
//        }
//
//        return new Responce(false, null);
//    }

    @Override
    public List<Order> getList(UUID userId) {
        List<Order> list = new ArrayList<>();

        for (Order order : orderList) {
            if(order.getUserId() == userId) {
                list.add(order);
            }
        }

        return list;
    }

    @Override
    public Order getNewOrder(UUID id) {
        for (Order order : orderList) {
            if(order.getDriverId() == id && order.getStatus() == OrderStatus.NEW) {
                return order;
            }
        }
        return null;
    }

    @Override
    public Order getOrderByUser(UUID userId) {
        for (Order order : orderList) {
            if(order.getUserId() == userId && order.getStatus() != OrderStatus.FINISH) {
                return order;
            }
        }
        return null;
    }

    @Override
    public Responce delete(UUID orderId) {
        for (int i = 0; i < orderList.size(); i++) {
            if(orderList.get(i).getId() == orderId && orderList.get(i).isActive()) {
                orderList.get(i).setActive(false);
                return new Responce(true, orderId);
            }
        }

        return new Responce(false, orderId);
    }

    @Override
    public List getList() {
        return orderList;
    }

    @Override
    public Order getOne(UUID id) {
        for (Order order : orderList) {
            if(order.getId() == id && order.getStatus() != OrderStatus.FINISH) {
                return order;
            }
        }
        return null;
    }

    @Override
    public boolean edit(UUID orderId) {
        for (int i = 0; i < orderList.size(); i++) {
            if(orderList.get(i).getId() == orderId && !orderList.get(i).isActive()) {
                orderList.get(i).setActive(true);
                return true;
            }
        }
        return false;
    }
}
