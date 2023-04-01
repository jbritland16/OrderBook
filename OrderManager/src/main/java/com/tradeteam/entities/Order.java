package com.tradeteam.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="orders")
@Getter @AllArgsConstructor @RequiredArgsConstructor @ToString
public class Order {
    @Id
    private int orderId;
    @NonNull private LocalDateTime orderTimestamp;
    @NonNull private int numberOrdered;
    @NonNull private int numberFulfilled = 0;
    @NonNull private double price;
    @NonNull private boolean orderActive = true;

    public enum OrderType {
        BUY,
        SELL
    }
    @NonNull private OrderType orderType;

    @NonNull private int userId;

    @NonNull private String companyAbbrev;

    @NonNull private String exchangeId;

}
