package com.tradeteam.TradingEngine.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
public class Exchange {

    @Id
    @NonNull
    private String exchangeId;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "exchange")
    @EqualsAndHashCode.Exclude
    private List<OrderBook> orderBooks = new ArrayList<>();

    @NonNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "exchange")
    @EqualsAndHashCode.Exclude
    private List<Trade> trades = new ArrayList<>();

    // private double feeLadder; This was included in Kishore's example but I would like
    // to do more research before implementing

    public void addOrder(Order order) {
        order.getOrderBook().addOrder(order);
    }

    public OrderBook getOrderBookByCompanyAbbrev(String companyAbbrev) {
        return orderBooks.stream()
                .filter(o -> o.getOrderBookId().getCompanyAbbrev() == companyAbbrev)
                .findFirst().get();
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

}
