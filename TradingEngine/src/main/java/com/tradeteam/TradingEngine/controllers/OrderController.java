package com.tradeteam.TradingEngine.controllers;

import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.entities.OrderBookId;
import com.tradeteam.TradingEngine.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/byUserId")
    public List<Order> getOrdersByUserId(@RequestBody int userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @PostMapping("/byOrderId")
    public Order getOrderById(@RequestBody int orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/new")
    public void addNewOrder(@RequestBody Order order) {
        orderService.addNewOrderToExchange(order);
    }

    @PostMapping("/wallet")
    public Map<OrderBookId, Integer> getWalletByUserId(@RequestBody int userId) {
        return orderService.getUserWallet(userId);
    }

}
