package com.tradeteam.TradingEngine.services;

import com.tradeteam.TradingEngine.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderViewerService {

    public List<Order> getOrdersByUserId(int userId);
    public Order getOrderById(int orderId);

}
