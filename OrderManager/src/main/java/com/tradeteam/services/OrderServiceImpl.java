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

    @Override
    public Order cancelOrder(int orderId, int userId) {
        // get order by id
        // check if order belongs to userId
        // update active = false
        // save order
        // return order
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        if(order.getUserId() != userId){
            new RuntimeException("Order doesn't belong to user");
        }
        order.setOrderActive(false);
        return orderRepository.save(order);
    }

    @Override
    public Order findById(int orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order updateOrder(int orderId, int numberOrdered, double price, String orderType, String companyAbbrev) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setNumberOrdered(numberOrdered);
        order.setPrice(price);
        order.setOrderType(Order.OrderType.valueOf(orderType));
        order.setCompanyAbbrev(companyAbbrev);
        return orderRepository.save(order);
    }


}
