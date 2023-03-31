package com.tradeteam.TradingEngine;

import com.tradeteam.TradingEngine.entities.*;
import com.tradeteam.TradingEngine.repositories.OrderBookRepository;
import com.tradeteam.TradingEngine.repositories.OrderRepository;
import com.tradeteam.TradingEngine.services.OrderService;
import com.tradeteam.TradingEngine.services.OrderServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WalletTest {

    @InjectMocks
    OrderService orderService = new OrderServiceImpl();
    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderBookRepository orderBookRepository;

    OrderBookId orderBookId;
    OrderBook orderBook1;
    Order buyOrder1;
    Order buyOrder2;
    Order buyOrder3;
    Order sellOrder1;
    Order sellOrder2;
    Map<OrderBookId, Integer> wallet;


    @BeforeEach
    public void setUp() {
        orderBookId = new OrderBookId("NYSE", "DB");
        orderBook1 = new OrderBook(orderBookId,
                "Deutsche Bank", new Exchange(),
                new ArrayList<Order>());
        buyOrder1 = new Order(1, 1,
                LocalDateTime.of(2023, 3, 28, 12, 30),
                orderBook1, 10, 0,
                5.00, Order.OrderType.BUY, true, new ArrayList<Trade>());
        buyOrder2 = new Order(2, 1,
                LocalDateTime.of(2023, 3, 28, 12, 35),
                orderBook1, 10, 5,
                10.00, Order.OrderType.BUY, true, new ArrayList<Trade>());
        buyOrder3 = new Order(6, 1,
                LocalDateTime.of(2023, 3, 28, 12, 35),
                orderBook1, 10, 10,
                10.00, Order.OrderType.BUY, true, new ArrayList<Trade>());
        sellOrder1 = new Order(3, 1,
                LocalDateTime.of(2023, 3, 28, 12, 40),
                orderBook1, 10, 0,
                5.00, Order.OrderType.SELL, true, new ArrayList<Trade>());
        sellOrder2 = new Order(4, 1,
                LocalDateTime.of(2023, 3, 28, 12, 45),
                orderBook1, 15, 10,
                10.00, Order.OrderType.SELL, true, new ArrayList<Trade>());
        wallet = new HashMap<>();
    }

    @Test
    public void oneBuyOrderNoneFulfilled() {
        when(orderRepository.findByUserId(1)).thenReturn(Arrays.asList(buyOrder1));
        wallet = orderService.getUserWallet(1);
        Assert.assertNull(wallet.get(orderBookId));
    }

    @Test
    public void oneBuyOrderSomeFulfilled() {
        when(orderRepository.findByUserId(1)).thenReturn(Arrays.asList(buyOrder2));
        wallet = orderService.getUserWallet(1);
        Assert.assertEquals(Integer.valueOf(5), wallet.get(orderBookId));
    }

    @Test
    public void oneSellOrderNonFulfilled() {
        when(orderRepository.findByUserId(1)).thenReturn(Arrays.asList(sellOrder1));
        wallet = orderService.getUserWallet(1);
        Assert.assertNull(wallet.get(orderBookId));
    }

    @Test
    public void oneSellOrderSomeFulfilled() {
        when(orderRepository.findByUserId(1)).thenReturn(Arrays.asList(sellOrder2));
        wallet = orderService.getUserWallet(1);
        Assert.assertNull(wallet.get(orderBookId));
    }

    @Test
    public void multipleBuyOrders() {
        when(orderRepository.findByUserId(1)).thenReturn(Arrays.asList(buyOrder1, buyOrder2));
        wallet = orderService.getUserWallet(1);
        Assert.assertEquals(Integer.valueOf(5), wallet.get(orderBookId));
    }

    @Test
    public void moreBoughtThanSold() {
        when(orderRepository.findByUserId(1)).thenReturn(Arrays.asList(
                buyOrder1, buyOrder2, buyOrder3, sellOrder2));
        wallet = orderService.getUserWallet(1);
        Assert.assertEquals(Integer.valueOf(5), wallet.get(orderBookId));
    }

    @Test
    public void moreSoldThanBought() {
        when(orderRepository.findByUserId(1)).thenReturn(Arrays.asList(
                buyOrder1, buyOrder2, sellOrder2));
        wallet = orderService.getUserWallet(1);
        Assert.assertNull(wallet.get(orderBookId));
    }

}
