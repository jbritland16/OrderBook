package com.tradeteam.controllers;

import com.tradeteam.entities.Order;
import com.tradeteam.entities.OrderBook;
import com.tradeteam.entities.Trade;
import com.tradeteam.security.OrderManagerUserDetails;
import com.tradeteam.services.OrderService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.swing.plaf.BorderUIResource;
import java.util.List;

@Controller
public class OrdersController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public String findByUserId(@AuthenticationPrincipal OrderManagerUserDetails userDetails,
                               Model model) {
        List<Order> orders = orderService.findByUserId(userDetails.getUserId());
        model.addAttribute("orders", orders);
        return "list_orders";
    }

    @GetMapping("/order/create")
    public String addNewOrder(Model model) {
        model.addAttribute("order", new Order());
        return "add_order";
    }

    @PostMapping("/order/create")
    public String createOrder(@AuthenticationPrincipal OrderManagerUserDetails userDetails,
                              @RequestParam("numberOrdered") int numberOrdered,
                              @RequestParam("price") double price,
                              @RequestParam("OrderType") String orderType,
                              @RequestParam("companyAbbrev") String companyAbbrev) {
        Order new_order = new Order(numberOrdered, price, orderType, userDetails.getUserId(), companyAbbrev, 1);
        orderService.createOrder(new_order);
        return "redirect:/orders";
    }

    @PostMapping("/order/cancel")
    public String cancelOrder(
            @AuthenticationPrincipal OrderManagerUserDetails userDetails,
            @RequestParam("orderId") int orderId) {
        int currentUserId = userDetails.getUserId();
        orderService.cancelOrder(orderId, currentUserId);
        return "redirect:/orders";
    }

    @GetMapping("/order/edit/{orderId}")
    public String editOrder(@PathVariable("orderId") int orderId,
                            Model model) {
        Order order = orderService.findById(orderId);
        model.addAttribute("order", order);
        return "edit_order";
    }

    @PostMapping("/order/update/{orderId}")
    public String updateOrder(@PathVariable("orderId") int orderId,
                              @RequestParam("numberOrdered") int numberOrdered,
                              @RequestParam("price") double price,
                              @RequestParam("OrderType") String orderType,
                              @RequestParam("companyAbbrev") String companyAbbrev) {
        orderService.updateOrder(orderId, numberOrdered, price, orderType, companyAbbrev);
        return "redirect:/orders";
    }

    @GetMapping("/order/{orderId}")
    public String getOrderDetails(@PathVariable("orderId") int orderId, Model model) {
        Order order = orderService.findById(orderId);
        model.addAttribute("order", order);
        return "view_order";
    }

    public List<Trade> getTradeHistory(int userId) {
        return null;
    }

    public OrderBook getOrderBook(String exchangeId, String companyAbbrev) {
        return null;
    }

}
