package com.tradeteam.TradingEngine.services;

import com.tradeteam.TradingEngine.entities.Exchange;
import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.entities.OrderBook;
import com.tradeteam.TradingEngine.entities.OrderBookId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExchangeService {

    public Exchange getExchangeByExchangeId(String exchangeId);
    public void addNewOrderToExchange(Order order);
    public OrderBook getOrderBookByOrderBookId(OrderBookId orderBookId);

}
