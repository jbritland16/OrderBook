package com.tradeteam.dtos;

import com.tradeteam.entities.Order;
import com.tradeteam.entities.OrderBook;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ReceiveOrderDTO {

    private int orderId;
    private LocalDateTime orderTimestamp;
    private OrderBook orderBook;
    private int numberOrdered;
    private int numberFulfilled;
    private double price;
    private Order.OrderType orderType;
    private boolean orderActive;

    public ReceiveOrderDTO(int orderId, LocalDateTime orderTimestamp,
                           int numberOrdered, int numberFulfilled,
                           double price, Order.OrderType orderType,
                           boolean orderActive) {
        this.orderId = orderId;
        this.orderTimestamp = orderTimestamp;
        this.numberOrdered = numberOrdered;
        this.numberFulfilled = numberFulfilled;
        this.price = price;
        this.orderType = orderType;
        this.orderActive = orderActive;
    }

    public ReceiveOrderDTO(int orderId, int userId, LocalDateTime orderTimestamp,
                           OrderBookDTO orderBook,
                           int numberOrdered, int numberFulfilled,
                           double price, Order.OrderType orderType,
                           boolean orderActive) {
        this.orderId = orderId;
        this.orderTimestamp = orderTimestamp;
        this.orderBook = orderBook.orderBook();
        this.numberOrdered = numberOrdered;
        this.numberFulfilled = numberFulfilled;
        this.price = price;
        this.orderType = orderType;
        this.orderActive = orderActive;
    }

    public Order order() {
        return new Order(orderId, orderTimestamp, numberOrdered,
                numberFulfilled, price, orderActive, orderType,
                0, orderBook.getCompanyAbbrev(),
                orderBook.getExchangeId());
    }

}
