package com.tradeteam.services;

import com.tradeteam.entities.Order;
import com.tradeteam.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository OrderRepository;

    public List<Order> findByUserId(int userId) {

        return OrderRepository.findByUserId(userId);
    }
}
