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
    // Method written by Vania moved over from OrderManager
    public Order addNewOrderToOrderBook(Order order, String exchangeId, String companyAbbrev) {
        OrderBook orderBook = orderBookRepository.findByOrderBookId(
                new OrderBookId(exchangeId, companyAbbrev));
        order.setOrderBook(orderBook);
        order.setExchangeId(exchangeId);
        order.setCompanyAbbrev(companyAbbrev);
        order = orderRepository.saveAndFlush(order);
        if (orderBook.matchOrder(order)) {
            orderBookRepository.saveAndFlush(orderBook);
        }
        orderRepository.flush();
        return orderRepository.findByOrderId(order.getOrderId());
    }

    @Override
    public Map<String, Integer> getUserWallet(int userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        Map<String, Integer> wallet = new HashMap<>();
        for (Order order : orders) {
            String orderBookIdString = (order.getOrderBook().getOrderBookId().getExchangeId() + ":"
                    + order.getOrderBook().getOrderBookId().getCompanyAbbrev());
            Integer currentTotal = wallet.get(orderBookIdString);
            if (currentTotal == null) {
                currentTotal = 0;
            }
            if (order.getOrderType() == Order.OrderType.BUY) {
                wallet.put(orderBookIdString, currentTotal + order.getNumberFulfilled());
            }
            else {
                wallet.put(orderBookIdString, currentTotal - order.getNumberFulfilled());
            }
        }
        return wallet.entrySet().stream().filter(x -> x.getValue() > 0)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

    @Override
    // Method written by Vania moved over from OrderManager
    public Order cancelOrder(int orderId, int userId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        if (order.getUserId() != userId) {
            throw new RuntimeException("Order doesn't belong to user");
        }
        else {
            order.setOrderActive(false);
            return orderRepository.save(order);
        }
    }

    @Override
    public Order updateOrder(Order order) {
        order.setOrderBook(orderBookRepository.findByOrderBookId(
                new OrderBookId(order.getExchangeId(), order.getCompanyAbbrev())));
        order = orderRepository.saveAndFlush(order);
        OrderBook orderBook = orderBookRepository.findByOrderBookId(
                new OrderBookId(order.getExchangeId(), order.getCompanyAbbrev()));
        if (orderBook.matchOrder(order)) {
            orderBookRepository.saveAndFlush(orderBook);
        }
        orderRepository.flush();
        return orderRepository.findByOrderId(order.getOrderId());
    }

}
