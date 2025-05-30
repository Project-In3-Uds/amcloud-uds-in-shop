package cm.amcloud.mobile.uds_in_shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cm.amcloud.mobile.uds_in_shop.exception.ResourceNotFoundException;
import cm.amcloud.mobile.uds_in_shop.model.Order;
import cm.amcloud.mobile.uds_in_shop.model.OrderItem;
import cm.amcloud.mobile.uds_in_shop.repository.OrderRepository;
import cm.amcloud.mobile.uds_in_shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        // Set the bidirectional relationship
        if (order.getOrderItems() != null) {
            for (OrderItem orderItem : order.getOrderItems()) {
                orderItem.setOrder(order);
            }
        }
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        Order existingOrder = getOrderById(id);

        // Update order details
        existingOrder.setTotalPrice(order.getTotalPrice());
        existingOrder.getOrderItems().clear();

        // Set bidirectional relationship for updated order items
        if (order.getOrderItems() != null) {
            for (OrderItem orderItem : order.getOrderItems()) {
                orderItem.setOrder(existingOrder);
                existingOrder.getOrderItems().add(orderItem);
            }
        }

        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }
}
