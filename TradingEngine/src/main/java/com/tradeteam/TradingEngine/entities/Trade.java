package com.tradeteam.TradingEngine.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
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
    @Column(scale = 2)
    private BigDecimal pricePerShare;

    @NonNull
    @JsonIgnore @Getter(onMethod = @__( @JsonIgnore ))
    @Column(scale = 2)
    private BigDecimal profitPerShare;

    public Trade(List<Order> orders, LocalDateTime tradeTimestamp, int numberTraded,
                 BigDecimal pricePerShare, BigDecimal profitPerShare) {
        this.orders = orders;
        this.tradeTimestamp = tradeTimestamp;
        this.numberTraded = numberTraded;
        this.pricePerShare = pricePerShare;
        this.profitPerShare = profitPerShare;
    }

    public static Trade of(Order order1, Order order2) {
        int numToTrade = Stream.of(order1, order2)
                .map(o -> o.getNumberOrdered() - o.getNumberFulfilled())
                .min(Integer::compare).get();
        order1.fulfillSome(numToTrade);
        order2.fulfillSome(numToTrade);
        BigDecimal price = BigDecimal.valueOf(Stream.of(order1, order2)
                .map(o -> o.getPrice())
                .max(Double::compare).get());
        BigDecimal profit = price.subtract(BigDecimal.valueOf(Stream.of(order1, order2)
                .map(o -> o.getPrice())
                .min(Double::compare).get()));
        return new Trade(Arrays.asList(order1, order2), LocalDateTime.now(),
                numToTrade, price, profit);
    }

}
