package com.tradeteam.TradingEngine.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name="exchange"),
            @JoinColumn(name="companyAbbrev")})
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

    @NonNull
    @ManyToMany
    @JoinTable(
            name = "order_trades",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "trade_id"))
    private List<Trade> trades;

}
