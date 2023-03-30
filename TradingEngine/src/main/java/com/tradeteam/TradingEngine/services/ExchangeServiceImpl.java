package com.tradeteam.TradingEngine.services;

import com.tradeteam.TradingEngine.entities.Exchange;
import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.entities.OrderBook;
import com.tradeteam.TradingEngine.entities.OrderBookId;
import com.tradeteam.TradingEngine.repositories.ExchangeRepository;
import com.tradeteam.TradingEngine.repositories.OrderBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeServiceImpl implements  ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private OrderBookRepository orderBookRepository;


    @Override
    public List<String> getAllExchangeIds() {
        return exchangeRepository.getAllExchangeIds();
    }

    @Override
    public void addNewOrderToExchange(Order order) {
        OrderBook orderBook = order.getOrderBook();
        orderBook.addOrder(order);
        orderBookRepository.save(orderBook);
    }

    @Override
    public OrderBook getOrderBookByOrderBookId(OrderBookId orderBookId) {
        System.out.println("Finding order book " + orderBookId);
        return orderBookRepository.findByOrderBookId(orderBookId);
    }
}
