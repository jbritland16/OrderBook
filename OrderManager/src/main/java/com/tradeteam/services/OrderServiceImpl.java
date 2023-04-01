package com.tradeteam.services;

import com.tradeteam.consumers.TradingEngineApiConsumer;
import com.tradeteam.entities.Order;
import com.tradeteam.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TradingEngineApiConsumer tradingEngineApiConsumer;

    public List<Order> findByUserId(int userId) {

        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }


}
