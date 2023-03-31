package com.tradeteam.TradingEngine.services;

import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.entities.OrderBook;
import com.tradeteam.TradingEngine.entities.OrderBookId;
import com.tradeteam.TradingEngine.repositories.OrderBookRepository;
import com.tradeteam.TradingEngine.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderBookRepository orderBookRepository;

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderRepository.getReferenceById(orderId);
    }

    @Override
    public void addNewOrderToExchange(Order order) {
        order = orderRepository.save(order);
        OrderBook orderBook = order.getOrderBook();
        if (orderBook.matchOrder(order)) {
            orderBookRepository.save(orderBook);
        }
    }

    @Override
    public Map<OrderBookId, Integer> getUserWallet(int userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        Map<OrderBookId, Integer> wallet = new HashMap<>();
        for (Order order : orders) {
            OrderBookId orderBookId = order.getOrderBook().getOrderBookId();
            Integer currentTotal = wallet.get(orderBookId);
            if (currentTotal == null) {
                currentTotal = 0;
            }
            if (order.getOrderType() == Order.OrderType.BUY) {
                wallet.put(orderBookId, currentTotal + order.getNumberFulfilled());
            }
            else {
                wallet.put(orderBookId, currentTotal - order.getNumberFulfilled());
            }
        }
        return wallet.entrySet().stream().filter(x -> x.getValue() > 0)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }


}
