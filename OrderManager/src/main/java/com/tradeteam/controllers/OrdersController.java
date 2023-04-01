package com.tradeteam.controllers;

import com.tradeteam.entities.Order;
import com.tradeteam.entities.OrderBook;
import com.tradeteam.entities.Trade;
import com.tradeteam.security.OrderManagerUserDetails;
import com.tradeteam.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;
@Controller
public class OrdersController {
    @Autowired
    OrderService orderService;

    public List<Order> findByUserId(int userId) {
        return orderService.findByUserId(userId);
    }

    public Order updateOrder(Order order) {
        return null;
    }
    @GetMapping("/order/create")
    public String addNewOrder() {
        return "order_form";
    }
    @PostMapping("/order/create")
    public String createOrder(@AuthenticationPrincipal OrderManagerUserDetails userDetails,
                              @RequestParam("numberOrdered") int numberOrdered,
                              @RequestParam("price") double price,
                              @RequestParam("OrderType") String orderType,
                              @RequestParam("companyAbbrev") String companyAbbrev,
                              Model model){
        Order new_order = new Order(numberOrdered, price, orderType, userDetails.getUserId(), companyAbbrev, 1);
        orderService.createOrder(new_order);
        model.addAttribute("message", "Your order was successfully created!");
        return "order_form";
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
