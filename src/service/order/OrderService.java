package service.order;

import models.order.Order;
import models.order.OrderStatus;
import models.responce.Responce;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService implements OrderInterface{
    public static  final List<Order> orderList = new ArrayList<>();

    @Override
    public Responce addOrder(Order order) {
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

    @Override
    public Responce showOrderProcess(UUID orderId) {
        for (int i = 0; i < orderList.size(); i++) {
            if(orderList.get(i).getId() == orderId) {
                return new Responce(true, orderList.get(i));
            }
        }

        return new Responce(false, null);
    }
}
