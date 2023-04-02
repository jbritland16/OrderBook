package com.tradeteam.services;

import com.tradeteam.entities.Order;

import java.util.List;

public interface OrderService {
    public List<Order> findByUserId(int userId);
    public Order createOrder(Order order);
    public Order cancelOrder(int orderId, int userId);
    public Order findById(int orderId);
}
