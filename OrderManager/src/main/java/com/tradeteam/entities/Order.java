package com.tradeteam.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter @AllArgsConstructor @RequiredArgsConstructor @ToString
public class Order {

    private int orderId;
    @NonNull private LocalDateTime orderTimestamp;
    @NonNull private int numberOrdered;
    @NonNull private int numberFulfilled;
    @NonNull private double price;
    @NonNull private boolean orderActive;

    public enum OrderType {
        BUY,
        SELL
    }
    @NonNull private OrderType orderType;

    @NonNull private int userId;

}
