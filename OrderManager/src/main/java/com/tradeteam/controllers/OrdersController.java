package com.tradeteam.controllers;

import com.tradeteam.entities.Order;
import com.tradeteam.entities.OrderBook;
import com.tradeteam.entities.Trade;
import com.tradeteam.repositories.OrderRepository;
import com.tradeteam.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.List;

public class OrdersController {
    @Autowired
    OrderService orderService;

    public List<Order> findByUserId(int userId) {
        return orderService.findByUserId(userId);
    }

    public Order updateOrder(Order order) {
        return null;
    }

    public Order addNewOrder(Order order) {
        return null;
    }

    public Order cancelOrder(int orderId) {
        return null;
    }

    public Order getOrderDetails(int orderId) {
        return null;
    }

    public List<Trade> getTradeHistory(int userId) {
        return null;
    }

    public OrderBook getOrderBook(String exchangeId, String companyAbbrev) {
        return null;
    }

}
