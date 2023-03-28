package com.tradeteam.TradingEngine.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Entity
@Getter @AllArgsConstructor @NoArgsConstructor @RequiredArgsConstructor
public class Order {

    public static Comparator<Order> highestPriceComparator = Comparator.comparing(Order::getPrice);
    public static Comparator<Order> lowestPriceComparator = highestPriceComparator.reversed();
    public static Comparator<Order> earliestTimestampComparator = Comparator.comparing(Order::getOrderTimestamp).reversed();

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

    public LocalDateTime getOrderTimestamp() {
        return orderTimestamp;
    }

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

    @NonNull
    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Trade> trades;

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
