package com.tradeteam.TradingEngine.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.entities.OrderBookId;
import com.tradeteam.TradingEngine.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.FieldView;
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

    @PostMapping("/{exchangeId}/{companyAbbrev}/new")
    public void addNewOrder(@RequestBody Order order,
                            @PathVariable String exchangeId,
                            @PathVariable String companyAbbrev) {
        orderService.addNewOrderToOrderBook(order, exchangeId, companyAbbrev);
    }

    @PostMapping("/wallet")
    public Map<String, Integer> getWalletByUserId(@RequestBody int userId) {
        return orderService.getUserWallet(userId);
    }

}
