package com.tradeteam.TradingEngine.controllers;

import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.services.OrderViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderViewerService orderViewerService;

    @PostMapping("/byUserId")
    public List<Order> getOrdersByUserId(@RequestBody int userId) {
        return orderViewerService.getOrdersByUserId(userId);
    }

    @PostMapping("/byOrderId")
    public Order getOrderById(@RequestBody int orderId) {
        return orderViewerService.getOrderById(orderId);
    }

}
