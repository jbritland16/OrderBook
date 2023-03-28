package com.tradeteam.TradingEngine.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Entity
@Getter @NoArgsConstructor @AllArgsConstructor @ToString @RequiredArgsConstructor
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tradeId")
    private int tradeId;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private Order order1;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private Order order2;

    @NonNull
    private LocalDateTime tradeTimestamp;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "exchangeId", referencedColumnName = "exchangeId", insertable = false, updatable = false)
    private Exchange exchange;

    public static Trade of(Order order1, Order order2) {
        int numToTrade = Stream.of(order1, order2)
                .map(o -> o.getNumberOrdered() - o.getNumberFulfilled())
                .min(Integer::compare).orElse(0);
        order1.fulfillSome(numToTrade);
        order2.fulfillSome(numToTrade);
        return new Trade(order1, order2, LocalDateTime.now(),
                order1.getOrderBook().getExchange());
    }

}
