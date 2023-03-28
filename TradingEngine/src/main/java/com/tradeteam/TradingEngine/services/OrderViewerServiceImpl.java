package com.tradeteam.TradingEngine.services;

import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.repositories.OrderRepositoryViewOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderViewerServiceImpl implements OrderViewerService {

    @Autowired
    private OrderRepositoryViewOnly orderRepository;

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return null;
    }

    @Override
    public Order getOrderById(int orderId) {
        return null;
    }
}
