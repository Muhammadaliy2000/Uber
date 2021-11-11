package service.order;

import models.order.Order;
import service.responce.Responce;

import java.util.UUID;

public interface OrderInterface {
    Responce addOrder(Order order);

    boolean cancelOrder(UUID orderId);

    Responce showOrderProcess(UUID orderId);
}
