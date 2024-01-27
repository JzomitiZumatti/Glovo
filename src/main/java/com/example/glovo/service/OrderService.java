package com.example.glovo.service;

import com.example.glovo.model.Order;
import com.example.glovo.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class OrderService {
    private static final Map<Long, Order> orders = new HashMap<>();
    private static long orderIdCounter = 0;
    private final ProductService productService;

    public Order get(long id) {
        return Optional.ofNullable(orders.get(id))
                .orElseThrow(() -> new NoSuchElementException("Order with ID " + id + " does not exist"));
    }

    public Map<Long, Order> get() {
        return orders;
    }

    public void save(Order order) {
        order.setId(orderIdCounter++);
        orders.put(order.getId(), order);
    }

    public Order update(Order newOrderData) {
        Order existingOrder = orders.get(newOrderData.getId());
        return Optional.ofNullable(existingOrder)
                .map(order -> {
                    order.setCustomerName(newOrderData.getCustomerName());
                    order.setCustomerNumber(newOrderData.getCustomerNumber());
                    order.setAddress(newOrderData.getAddress());
                    order.setProducts(newOrderData.getProducts());
                    order.setTotalPrice(newOrderData.getTotalPrice());
                    orders.put(newOrderData.getId(), order);
                    return order;
                })
                .orElseThrow(() -> new NoSuchElementException("Order with ID " + newOrderData.getId() + " does not exist"));
    }

    public Order add(long orderId, long productId) {
        Order existingOrder = Optional.ofNullable(orders.get(orderId))
                .orElseThrow(() -> new IllegalArgumentException("Order with ID " + orderId + " does not exist"));
        Product product = Optional.ofNullable(productService.getProductId(productId))
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + orderId + " does not exist"));
        existingOrder.getProducts().add(product);
        return existingOrder;
    }

    public Order add(long orderId, Product product) {
        Order existingOrder = Optional.ofNullable(orders.get(orderId))
                .orElseThrow(() -> new IllegalArgumentException("Order with ID " + orderId + " does not exist"));
        existingOrder.getProducts().add(productService.add(product));
        return existingOrder;
    }

    public Order delete(long orderId, long productId) {
        Order existingOrder = Optional.ofNullable(orders.get(orderId))
                .orElseThrow(() -> new IllegalArgumentException("Order with ID " + orderId + " does not exist"));
        Product product = Optional.ofNullable(productService.getProductId(productId))
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + productId + " does not exist"));
        existingOrder.getProducts().remove(product);
        return existingOrder;
    }

    public void delete(long id) {
        Optional.ofNullable(orders.remove(id))
                .orElseThrow(() -> new NoSuchElementException("Order with ID " + id + " does not exist"));
    }
}
