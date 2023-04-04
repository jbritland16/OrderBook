package com.tradeteam.TradingEngine.services;

import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.entities.OrderBook;
import com.tradeteam.TradingEngine.entities.OrderBookId;

import java.util.List;
import java.util.Map;

public interface OrderService {

    public List<Order> getOrdersByUserId(int userId);
    public Order getOrderById(int orderId);
    public Order addNewOrderToOrderBook(Order order, String exchangeId, String companyAbbrev);
    public Map<String, Integer> getUserWallet(int userId);
    public Order cancelOrder(int orderId, int userId);
    public Order updateOrder(Order order);

}
