package com.tradeteam.TradingEngine.controllers;

import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.entities.OrderBook;
import com.tradeteam.TradingEngine.entities.OrderBookId;
import com.tradeteam.TradingEngine.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
public class ExchangeController {

    @Autowired
    ExchangeService exchangeService;

    @GetMapping("/all")
    public List<String> getExchangeByExchangeId() {
        return exchangeService.getAllExchangeIds();
    }

    @PostMapping("/addOrder")
    public void addNewOrderToExchange(@RequestBody Order order) {
        exchangeService.addNewOrderToExchange(order);
    }

    @GetMapping("/{exchangeId}/{companyAbbrev}")
    public OrderBook getOrderBookByOrderBookId(@PathVariable String exchangeId,
                                               @PathVariable String companyAbbrev) {
        return exchangeService.getOrderBookByOrderBookId(
                new OrderBookId(exchangeId, companyAbbrev));
    }

}
