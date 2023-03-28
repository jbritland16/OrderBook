package com.tradeteam.services;

import com.tradeteam.entities.Order;

import java.util.List;

public interface OrderService {
    public List<Order> findByUserId(int userId);
}
