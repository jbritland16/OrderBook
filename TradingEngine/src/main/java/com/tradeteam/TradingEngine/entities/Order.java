package com.tradeteam.TradingEngine.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Getter @AllArgsConstructor @NoArgsConstructor @RequiredArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Order implements Comparable<Order> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @NonNull
    private int userId;
    @NonNull
    private LocalDateTime orderTimestamp;

    @Column(name = "companyAbbrev", insertable=false, updatable=false)
    @JoinColumn(name = "companyAbbrev")
    String companyAbbrev;

    @Column(name = "exchangeId", insertable=false, updatable=false)
    @JoinColumn(name = "exchangeId")
    String exchangeId;

    @JsonIgnore @Getter(onMethod = @__( @JsonIgnore ))
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    @Override
    public int compareTo(Order o) {
        if (this.orderType != o.getOrderType()) {
            return this.orderType.compareTo(o.getOrderType());
        }
        else if (this.price == o.getPrice()) {
            return this.orderTimestamp.compareTo(o.getOrderTimestamp());
        }
        else if (this.orderType == OrderType.BUY) {
            // This is structured like a queue and the highest buy price should be first;
            // therefore, we reverse the order
            return Double.compare(o.getPrice(), this.price);
        }
        else {
            return Double.compare(this.price, o.getPrice());
        }
    }

    public boolean confirmTradeWithOrder(Order order) {
        if (this.orderType == OrderType.BUY) {
            return this.price >= order.getPrice();
        }
        else {
            return this.price <= order.getPrice();
        }
    }

    public enum OrderType {
        BUY,
        SELL
    }

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
