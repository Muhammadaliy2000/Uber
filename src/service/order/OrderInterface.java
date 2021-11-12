package service.order;

import models.order.Order;
import models.responce.Responce;

import java.util.List;
import java.util.UUID;

public interface OrderInterface {
    Responce addOrder(Order order);

    boolean cancelOrder(UUID orderId);

    Responce showOrderProcess(UUID orderId);

    Order getOrder(UUID id);

    List<Order> getList(UUID id);
}
