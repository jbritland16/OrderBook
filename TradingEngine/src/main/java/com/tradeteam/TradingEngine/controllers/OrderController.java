package com.tradeteam.TradingEngine.controllers;

import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.services.OrderViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    OrderViewerService orderViewerService;

    @GetMapping("/byUser")
    public List<Order> getOrdersByUserId(@RequestBody int userId) {
        return orderViewerService.getOrdersByUserId(userId);
    }

    @GetMapping("/byId")
    public Order getOrderById(@RequestBody int orderId) {
        return orderViewerService.getOrderById(orderId);
    }

}
