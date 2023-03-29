package com.tradeteam.TradingEngine;

import com.tradeteam.TradingEngine.entities.Exchange;
import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.entities.OrderBook;
import com.tradeteam.TradingEngine.entities.Trade;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddOrderToExchangeTest {

    Exchange exchange1;
    OrderBook orderBook1;
    Order buyOrder1;
    Order buyOrder2;
    Order sellOrder1;
    Order sellOrder2;
    Order sellOrder3;

    @BeforeEach
    public void setUp() {
        exchange1 = new Exchange("NYSE", new ArrayList<OrderBook>(),
                new ArrayList<Trade>());
        exchange1.getOrderBooks().add(orderBook1);
        orderBook1 = new OrderBook("AAPL", exchange1,
                new ArrayList<Order>());
        buyOrder1 = new Order(1,
                LocalDateTime.of(2023, 3, 28, 12, 30),
                orderBook1, 10, 0,
                15.00, Order.OrderType.BUY, true, new ArrayList<Trade>());
        buyOrder2 = new Order(1,
                LocalDateTime.of(2023, 3, 28, 12, 35),
                orderBook1, 5, 0,
                16.00, Order.OrderType.BUY, true, new ArrayList<Trade>());
        sellOrder1 = new Order(2,
                LocalDateTime.of(2023, 3, 28, 12, 40),
                orderBook1, 10, 0,
                14.00, Order.OrderType.SELL, true, new ArrayList<Trade>());
        sellOrder2 = new Order(2,
                LocalDateTime.of(2023, 3, 28, 12, 45),
                orderBook1, 15, 0,
                17.00, Order.OrderType.SELL, true, new ArrayList<Trade>());
        sellOrder3 = new Order(2,
                LocalDateTime.of(2023, 3, 28, 12, 45),
                orderBook1, 12, 0,
                11.00, Order.OrderType.SELL, true, new ArrayList<Trade>());

        // buyOrder1: qty 10, price 15
        // buyOrder2: qty 5, price 16
        // sellOrder1: qty 10, price 14
        // sellOrder2: qty 15, price 17
        // sellOrder3: qty 12, price 11
    }

    @Test
    public void addOrderWithNoOtherOrders() {
        exchange1.addOrder(buyOrder1);
        Assert.assertEquals(buyOrder1, orderBook1.getOrders().get(0));
    }

    @Test
    public void addOrderWithNonMatchingOrder() {
        exchange1.addOrder(buyOrder1);
        exchange1.addOrder(buyOrder2);
        Assert.assertEquals(buyOrder2, orderBook1.getOrders().get(1));
    }

    @Test
    public void addSellOrderWithOneBuyOrder() {
        exchange1.addOrder(buyOrder1);
        exchange1.addOrder(sellOrder1);
        Assert.assertEquals(10, orderBook1.getOrders().get(0).getNumberFulfilled());
        Assert.assertEquals(10, orderBook1.getOrders().get(1).getNumberFulfilled());
        Assert.assertEquals(10, exchange1.getTrades().get(0).getNumberTraded());
        Assert.assertEquals(10, buyOrder1.getTrades().get(0).getNumberTraded());
        Assert.assertEquals(buyOrder1.getTrades().get(0).hashCode(), exchange1.getTrades().get(0).hashCode());
    }

    @Test
    public void addSellOrderWithWorsePriceThanBuyOrders() {
        exchange1.addOrder(buyOrder1);
        exchange1.addOrder(buyOrder2);
        exchange1.addOrder(sellOrder2);
        Assert.assertEquals(0, sellOrder2.getNumberFulfilled());
        Assert.assertEquals(0, exchange1.getTrades().size());
    }

    @Test
    public void addSellOrderThatSplitsBuyOrders() {
        exchange1.addOrder(buyOrder1);
        exchange1.addOrder(buyOrder2);
        exchange1.addOrder(sellOrder3);
        Assert.assertEquals(5, buyOrder2.getNumberFulfilled());
        Assert.assertEquals(7, buyOrder1.getNumberFulfilled());
        Assert.assertEquals(12, sellOrder3.getNumberFulfilled());
        Assert.assertTrue(buyOrder1.isOrderActive());
        Assert.assertFalse(buyOrder2.isOrderActive());
        Assert.assertFalse(sellOrder3.isOrderActive());
        Assert.assertEquals(2, exchange1.getTrades().size());
    }

    @Test
    public void twoIdenticalBuyOrdersExceptTimestamp() {
        Order buyOrderClone = new Order(1,
                LocalDateTime.of(2023, 3, 28, 12, 0),
                orderBook1, 10, 0,
                15.00, Order.OrderType.BUY, true, new ArrayList<Trade>());
        exchange1.addOrder(buyOrder1);
        exchange1.addOrder(buyOrderClone);
        exchange1.addOrder(sellOrder3);
        Assert.assertEquals(2, buyOrder1.getNumberFulfilled());
        Assert.assertEquals(10, buyOrderClone.getNumberFulfilled());
        Assert.assertEquals(12, sellOrder3.getNumberFulfilled());
        Assert.assertEquals(2, exchange1.getTrades().size());
    }

}
