package com.tradeteam.controllers;

import com.tradeteam.entities.Order;
import com.tradeteam.entities.OrderBook;
import com.tradeteam.dtos.TradeDTO;
import com.tradeteam.entities.Trade;
import com.tradeteam.security.OrderManagerUserDetails;
import com.tradeteam.services.ExchangeOrderBookService;
import com.tradeteam.services.OrderService;
import com.tradeteam.services.TradingEngineTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrdersController {
    @Autowired
    OrderService orderService;

    @Autowired
    ExchangeOrderBookService exchangeOrderBookService;

    @Autowired
    TradingEngineTradeService tradingEngineTradeService;

    @GetMapping("/orders")
    public String findByUserId(@AuthenticationPrincipal OrderManagerUserDetails userDetails,
                               Model model) {
        List<Order> orders = orderService.findByUserId(userDetails.getUserId());
        model.addAttribute("orders", orders);
        return "list_orders";
    }

    @GetMapping("/order/create")
    public String addNewOrder(Model model) {
        HashMap<String, List<String>> exchangeCompanyAbbrevs = tradingEngineTradeService.getExchangeIdsAndCompanyAbbrevs();
        model.addAttribute("exchangeCompanyAbbrevs", exchangeCompanyAbbrevs);
        model.addAttribute("order", new Order());
        return "add_order";
    }

    @PostMapping("/order/create")
    public String createOrder(@AuthenticationPrincipal OrderManagerUserDetails userDetails,
                              @RequestParam("numberOrdered") int numberOrdered,
                              @RequestParam("price") double price,
                              @RequestParam("OrderType") String orderType,
                              @RequestParam("companyAbbrev") String companyAbbrev,
                              @RequestParam("exchangeId") String exchangeId) {
        Order newOrder = new Order(numberOrdered, price, orderType, userDetails.getUserId(), companyAbbrev, exchangeId);
        orderService.createOrder(newOrder);
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
        HashMap<String, List<String>> exchangeCompanyAbbrevs = tradingEngineTradeService.getExchangeIdsAndCompanyAbbrevs();
        model.addAttribute("exchangeCompanyAbbrevs", exchangeCompanyAbbrevs);
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

    @GetMapping("/order/tradeHistory")
    public String getTradeHistory(@AuthenticationPrincipal OrderManagerUserDetails userDetails,
                                  Model model) {
        int currentUserId = userDetails.getUserId();
        List<Trade> trades = tradingEngineTradeService.getTrades(currentUserId);
        model.addAttribute("trades", trades);
        return "list_trade_history";
    }

    @GetMapping("/orderBook/{exchangeId}/{companyAbbrev}")
    public String getOrderBook(@PathVariable String exchangeId,
                               @PathVariable String companyAbbrev, Model model) {
        OrderBook orderBook = exchangeOrderBookService
                .getOrderBookByExchangeIdCompanyAbbrev(exchangeId, companyAbbrev);
        model.addAttribute("orderBook", orderBook);
        return "order_book"; // This view hasn't been made yet
    }

}
