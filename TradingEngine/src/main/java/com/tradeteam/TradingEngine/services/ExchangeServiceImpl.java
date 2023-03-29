package com.tradeteam.TradingEngine.services;

import com.tradeteam.TradingEngine.entities.Exchange;
import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.entities.OrderBook;
import com.tradeteam.TradingEngine.entities.OrderBookId;
import com.tradeteam.TradingEngine.repositories.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceImpl implements  ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;


    @Override
    public Exchange getExchangeByExchangeId(String exchangeId) {
        return exchangeRepository.findByExchangeId(exchangeId);
    }

    @Override
    public void addNewOrderToExchange(Order order) {
        Exchange exchange = order.getOrderBook().getExchange();
        exchange.addOrder(order);
        exchangeRepository.save(exchange);
    }

    @Override
    public OrderBook getOrderBookByOrderBookId(OrderBookId orderBookId) {
        Exchange exchange = exchangeRepository.findByExchangeId(orderBookId.getExchangeId());
        return exchange.getOrderBooks().stream()
                .filter(o -> o.getOrderBookId().equals(orderBookId))
                .findFirst().orElse(null);
    }
}
