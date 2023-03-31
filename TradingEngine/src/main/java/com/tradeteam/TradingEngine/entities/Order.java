package com.tradeteam.TradingEngine.entities;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Getter @AllArgsConstructor @NoArgsConstructor @RequiredArgsConstructor
public class Order {

    public static Comparator<Order> highestPriceComparator = Comparator.comparing(Order::getPrice);
    public static Comparator<Order> lowestPriceComparator = highestPriceComparator.reversed();
    public static Comparator<Order> earliestTimestampComparator = Comparator.comparing(Order::getOrderTimestamp).reversed();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @NonNull
    private int userId;
    @NonNull
    private LocalDateTime orderTimestamp;

    @JsonIgnore @Getter(onMethod = @__( @JsonIgnore ))
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name="companyAbbrev"),
            @JoinColumn(name="exchangeId")})
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
    @Setter
    private OrderType orderType;

    @NonNull
    @Setter
    private boolean orderActive;

    @JsonIgnore @Getter(onMethod = @__( @JsonIgnore )) @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Trade> trades = new ArrayList<>() {
    };

    public OrderType matchOrderType() {
        if (orderType == OrderType.SELL) {
            return OrderType.BUY;
        }
        else {
            return OrderType.SELL;
        }
    }

    public void fulfillSome(int numberToFulfill) {
        this.numberFulfilled += numberToFulfill;
        if (this.numberFulfilled == this.numberOrdered) {
            orderActive = false;
        }
    }

    public void addTrade(Trade trade) {
        this.trades.add(trade);
    }

}
