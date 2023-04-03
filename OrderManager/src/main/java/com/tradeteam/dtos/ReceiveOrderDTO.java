package com.tradeteam.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tradeteam.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceiveOrderDTO {

    private int orderId;
    private int userId;
    private LocalDateTime orderTimestamp;
    private String exchangeId;
    private  String companyAbbrev;
    private int numberOrdered;
    private int numberFulfilled;
    private double price;
    private Order.OrderType orderType;
    private boolean orderActive;

    public ReceiveOrderDTO(int orderId, LocalDateTime orderTimestamp,
                           String exchangeId, String companyAbbrev,
                           int numberOrdered, int numberFulfilled,
                           double price, Order.OrderType orderType,
                           boolean orderActive) {
        this.orderId = orderId;
        this.orderTimestamp = orderTimestamp;
        this.exchangeId = exchangeId;
        this.companyAbbrev = companyAbbrev;
        this.numberOrdered = numberOrdered;
        this.numberFulfilled = numberFulfilled;
        this.price = price;
        this.orderType = orderType;
        this.orderActive = orderActive;
    }

    public Order order() {
        return new Order(orderId, orderTimestamp, numberOrdered,
                numberFulfilled, price, orderActive, orderType,
                0, companyAbbrev, exchangeId);
    }

}
