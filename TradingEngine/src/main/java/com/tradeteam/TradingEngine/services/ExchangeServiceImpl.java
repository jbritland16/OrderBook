package com.tradeteam.TradingEngine.services;

import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.entities.OrderBook;
import com.tradeteam.TradingEngine.entities.OrderBookId;
import com.tradeteam.TradingEngine.repositories.ExchangeRepository;
import com.tradeteam.TradingEngine.repositories.OrderBookRepository;
import com.tradeteam.TradingEngine.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeServiceImpl implements  ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private OrderBookRepository orderBookRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<String> getAllExchangeIds() {
        return exchangeRepository.getAllExchangeIds();
    }

    @Override
    public List<String> getAllCompanyAbbrevsByExchangeId(String exchangeId) {
        return orderBookRepository.getAllCompanyAbbrevsByExchangeId(exchangeId);
    }

    @Override
    public OrderBook getOrderBookByOrderBookId(OrderBookId orderBookId) {
        System.out.println("Finding order book " + orderBookId);
        return orderBookRepository.findByOrderBookId(orderBookId);
    }
}
