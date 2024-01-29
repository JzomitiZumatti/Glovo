package com.example.glovo.contoller;

import com.example.glovo.model.Order;
import com.example.glovo.model.Product;
import com.example.glovo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping("/{id}")
    public Order get(@PathVariable long id) {
        return orderService.get(id);
    }
    @GetMapping
    public Map<Long, Order> get() {
        return orderService.get();
    }

    @PostMapping
    public void save(Order order) {
        orderService.save(order);
    }

    @PutMapping
    public Order update(@RequestBody Order order) {
        return orderService.update(order);
    }

    @PatchMapping("/{orderId}/products/{productId}")
    public Order add(@PathVariable long orderId, @PathVariable long productId) {
        return orderService.add(orderId, productId);
    }

    @PatchMapping("/{id}/products")
    public Order add(@PathVariable long id, @RequestBody Product product) {
        return orderService.add(id, product);
    }

    @DeleteMapping("/{orderId}/products/{productId}")
    public Order delete(@PathVariable long orderId, @PathVariable long productId) {
        return orderService.delete(orderId, productId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        orderService.delete(id);
    }
}
