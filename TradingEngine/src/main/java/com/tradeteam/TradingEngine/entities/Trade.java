package com.tradeteam.TradingEngine.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Entity
@Getter @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor @EqualsAndHashCode
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tradeId")
    private int tradeId;

    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private List<Order> orders = new ArrayList<>();

    @NonNull
    private LocalDateTime tradeTimestamp;

    @NonNull
    private int numberTraded;

    @NonNull
    private double pricePerShare;

    public Trade(List<Order> orders, LocalDateTime tradeTimestamp, int numberTraded, double pricePerShare) {
        this.orders = orders;
        this.tradeTimestamp = tradeTimestamp;
        this.numberTraded = numberTraded;
        this.pricePerShare = pricePerShare;
    }

    public static Trade of(Order order1, Order order2) {
        int numToTrade = Stream.of(order1, order2)
                .map(o -> o.getNumberOrdered() - o.getNumberFulfilled())
                .min(Integer::compare).orElse(0);
        order1.fulfillSome(numToTrade);
        order2.fulfillSome(numToTrade);
        double price = Stream.of(order1, order2)
                .map(o -> o.getPrice())
                .min(Double::compare).get();
        return new Trade(Arrays.asList(order1, order2), LocalDateTime.now(),
                numToTrade, price);
    }

}
