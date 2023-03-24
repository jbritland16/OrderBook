package com.tradeteam.TradingEngine.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @AllArgsConstructor @NoArgsConstructor @ToString @RequiredArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @NonNull
    private int userId;
    @NonNull
    private LocalDateTime orderTimestamp;

    @NonNull
    @OneToMany(mappedBy = "orderBookId", cascade = CascadeType.ALL)
    private OrderBook orderBook;

    @NonNull
    @Setter private int numberOrdered;
    @NonNull
    @Setter private int numberFulfilled;
    @NonNull
    @Setter private double price;

    public enum OrderType {
        BUY,
        SELL
    }
    @NonNull
    private OrderType orderType;

    @NonNull
    private boolean orderActive;

}
