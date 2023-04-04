package com.tradeteam.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tradeteam.entities.Order;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExistingOrderDTO {

    private int orderId;
    private int userId;
    private LocalDateTime orderTimestamp;
    @NonNull private String exchangeId;
    @NonNull private String companyAbbrev;
    private int numberOrdered;
    private int numberFulfilled;
    private double price;
    private Order.OrderType orderType;
    private boolean orderActive;

    public ExistingOrderDTO(int orderId, LocalDateTime orderTimestamp,
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
        System.out.println(this.orderId + " " + this.orderTimestamp + " " + this.exchangeId
        + " " + this.companyAbbrev + " " + this.numberOrdered + " " + this.numberFulfilled
        + " " + this.price + " " + this.orderType + " " + this.orderActive);
        return new Order(orderId, orderTimestamp, numberOrdered,
                numberFulfilled, price, orderActive, orderType,
                0, companyAbbrev, exchangeId);
    }

}
