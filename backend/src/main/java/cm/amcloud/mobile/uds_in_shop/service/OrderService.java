package cm.amcloud.mobile.uds_in_shop.service;

import java.util.List;

import cm.amcloud.mobile.uds_in_shop.model.Order;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    Order updateOrder(Long id, Order order);
    void deleteOrder(Long id);
}
