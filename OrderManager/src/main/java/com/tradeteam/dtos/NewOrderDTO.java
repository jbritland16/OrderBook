package com.tradeteam.dtos;

import com.tradeteam.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor
public class NewOrderDTO {

    private int userId;
    private LocalDateTime orderTimestamp;
    private int numberOrdered;
    private int numberFulfilled;
    private double price;
    private Order.OrderType orderType;
    private boolean orderActive;

    public static NewOrderDTO of(Order order) {
        return new NewOrderDTO(order.getUserId(), order.getOrderTimestamp(),
                order.getNumberOrdered(), order.getNumberFulfilled(),
                order.getPrice(), order.getOrderType(),
                order.isOrderActive());
    }

}
