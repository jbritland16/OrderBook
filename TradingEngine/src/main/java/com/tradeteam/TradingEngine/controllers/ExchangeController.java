package com.tradeteam.TradingEngine.controllers;

import com.tradeteam.TradingEngine.entities.Exchange;
import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.entities.OrderBook;
import com.tradeteam.TradingEngine.entities.OrderBookId;
import com.tradeteam.TradingEngine.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exchanges")
public class ExchangeController {

    @Autowired
    ExchangeService exchangeService;

    @GetMapping("/byId")
    public Exchange getExchangeByExchangeId(String exchangeId) {
        return exchangeService.getExchangeByExchangeId(exchangeId);
    }

    @PostMapping("/addOrder")
    public void addNewOrderToExchange(Order order) {
        exchangeService.addNewOrderToExchange(order);
    }

    @GetMapping("/orderBook")
    public OrderBook getOrderBookByOrderBookId(OrderBookId orderBookId) {
        return exchangeService.getOrderBookByOrderBookId(orderBookId);
    }

}
