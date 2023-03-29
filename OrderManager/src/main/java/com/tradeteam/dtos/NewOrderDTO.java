package com.tradeteam.dtos;

import com.tradeteam.entities.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @AllArgsConstructor
public class NewOrderDTO {

    private int orderId;
    private int userId;
    private LocalDateTime orderTimestamp;
    private OrderBookDTO orderBook;
    private int numberOrdered;
    private int numberFulfilled;
    private double price;
    private Order.OrderType orderType;
    private boolean orderActive;

    public static NewOrderDTO of(Order order) {
        return new NewOrderDTO(order.getOrderId(), order.getUserId(),
                order.getOrderTimestamp(),
                new OrderBookDTO(new OrderBookId(order.getExchangeId(), order.getCompanyAbbrev())),
                order.getNumberOrdered(), order.getNumberFulfilled(),
                order.getPrice(), order.getOrderType(),
                order.isOrderActive());
    }

}
