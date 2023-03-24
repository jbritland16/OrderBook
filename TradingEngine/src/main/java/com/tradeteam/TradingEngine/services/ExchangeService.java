package com.tradeteam.TradingEngine.services;

import com.tradeteam.TradingEngine.entities.Exchange;
import com.tradeteam.TradingEngine.entities.Order;

import java.util.List;

public interface ExchangeService {

    public Exchange getExchangeByExchangeId(String exchangeId);
    public List<String> getAllExchangeIds();
    public Order addNewOrderToExchange(Order order, Exchange exchange);

}
