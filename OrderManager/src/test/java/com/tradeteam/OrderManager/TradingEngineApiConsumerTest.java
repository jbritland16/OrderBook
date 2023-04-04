package com.tradeteam.OrderManager;

import com.tradeteam.consumers.TradingEngineApiConsumer;
import com.tradeteam.dtos.ExistingOrderDTO;
import com.tradeteam.dtos.NewOrderDTO;
import com.tradeteam.dtos.OrderBookDTO;
import com.tradeteam.entities.Order;
import com.tradeteam.entities.OrderBook;
import com.tradeteam.entities.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
@Disabled
public class TradingEngineApiConsumerTest {

    @Autowired
    TradingEngineApiConsumer apiConsumer;

    @Test
    public void listExchangeIdsTest() {
        List<String> exchangeIds = apiConsumer.getAllExchangeIds();
        System.out.println(exchangeIds);
        Assert.assertTrue(exchangeIds.size() > 0);
    }

    @Test
    public void getCompanyAbbrevsTest() {
        List<String> companyAbbrevs = apiConsumer.getAllCompanyAbbrevsByExchangeId("LSE");
        System.out.println(companyAbbrevs);
        Assert.assertTrue(companyAbbrevs.size() > 0);
    }

    @Test
    public void getOrderBookTest() {
        OrderBookDTO orderBookDTO = apiConsumer
                .getOrderBookByOrderBookId("NYSE", "KO");
        OrderBook orderBook = orderBookDTO.orderBook();
        System.out.println(orderBook);
        Assert.assertTrue(orderBook.getOrders().size() > 0);
    }

    @Test
    public void getExchangeTotalValueTradedTest() {
        Double valueTraded = apiConsumer.getTotalValueTradedByExchangeByDate(
                "NYSE",2023, 3, 23);
        Assert.assertEquals(145.75, valueTraded, 2);
    }

    @Test
    public void getOrdersByUserIdTest() {
        List<Order> orders = apiConsumer.getOrdersByUserId(8).stream()
                .map(odto -> odto.order()).collect(Collectors.toList());
        System.out.println(orders);
        Assert.assertTrue(orders.size() > 0);
    }

    @Test
    public void getOrderByOrderIdTest() {
        Order order = apiConsumer.getOrderById(6).order();
        System.out.println(order);
        Assert.assertEquals(6, order.getOrderId());
    }

    @Test
    public void addNewOrderTest() {
        Order order = new Order(LocalDateTime.now(), 5, 9.80,
                Order.OrderType.BUY, 3, "DB", "NYSE");
        Order newOrder = apiConsumer.addNewOrder(NewOrderDTO.of(order),
                order.getExchangeId(), order.getCompanyAbbrev()).order();
        Assert.assertEquals(5, newOrder.getNumberOrdered());
        Assert.assertEquals(9.80, newOrder.getPrice(), 2);
        Assert.assertEquals(Order.OrderType.BUY, newOrder.getOrderType());
        Assert.assertEquals(0, newOrder.getUserId());
        Assert.assertEquals("DB", newOrder.getCompanyAbbrev());
        Assert.assertEquals("NYSE", newOrder.getExchangeId());
    }

    @Test
    public void getWalletTest() {
        Map<String, Integer> wallet = apiConsumer.getWalletByUserId(5);
        System.out.println(wallet);
        Assert.assertTrue(wallet.size() > 0);
    }

    @Test
    public void getTradesByTradeIdTest() {
        Trade trade = apiConsumer.getTradeDetails(1).tradeForUser(7);
        System.out.println(trade);
        Assert.assertNotNull(trade);
    }

    @Test
    public void getTradesByUserIdTest() {
        List<Trade> trades = apiConsumer.getTradesByUser(9)
                .stream().map(tdto -> tdto.tradeForUser(9))
                .collect(Collectors.toList());
        System.out.println(trades);
        Assert.assertTrue(trades.size() > 0);
    }

    @Test
    public void cancelOrderTest() {
        Order cancelledOrder = apiConsumer.cancelOrder(new int[] {4, 6}).order();
        Assert.assertFalse(cancelledOrder.isOrderActive());
    }

    @Test
    public void updateOrderTest() {
        Order order = apiConsumer.updateOrder(new ExistingOrderDTO(10, LocalDateTime.now(),
                "NYSE", "BCS", 5, 0,
                7.06, Order.OrderType.BUY, true)).order();
        Assert.assertEquals(5, order.getNumberFulfilled());
        Assert.assertEquals(5, order.getNumberOrdered());
        Assert.assertFalse(order.isOrderActive());
        Assert.assertEquals("BCS", order.getCompanyAbbrev());
        Assert.assertEquals(Order.OrderType.BUY, order.getOrderType());
    }

}
