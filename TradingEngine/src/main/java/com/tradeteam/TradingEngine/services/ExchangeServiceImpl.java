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
import java.util.stream.Collectors;

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
    public OrderBook getOrderBookCurrentOrdersByOrderBookId(OrderBookId orderBookId) {
        OrderBook orderBook = orderBookRepository.findByOrderBookId(orderBookId);
        orderBook.setOrders(orderBook.getOrders().stream()
                .filter(o -> o.isOrderActive())
                .collect(Collectors.toList()));
        return orderBook;
    }

    @Override
    public Double[] getBestBuyAndSellPricesByOrderBookId(OrderBookId orderBookId) {
        List<Order> orders = orderBookRepository.findByOrderBookId(orderBookId)
                .getOrders();
        double bestBuyPrice = orders.stream()
                .filter(order -> order.getOrderType() == Order.OrderType.BUY)
                .filter(Order::isOrderActive)
                .map(Order::getPrice)
                .max(Double::compareTo)
                .orElse(Double.valueOf(0));
        double bestSellPrice = orders.stream()
                .filter(order -> order.getOrderType() == Order.OrderType.SELL)
                .filter(Order::isOrderActive)
                .map(Order::getPrice)
                .min(Double::compareTo)
                .orElse(Double.valueOf(0));
        return new Double[]{bestBuyPrice, bestSellPrice};
    }
}
