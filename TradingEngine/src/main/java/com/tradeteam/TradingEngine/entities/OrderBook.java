package com.tradeteam.TradingEngine.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class OrderBook {

    @EmbeddedId
    private OrderBookId orderBookId;

    private String companyName;

    public OrderBook(OrderBookId orderBookId) {
        this.orderBookId = orderBookId;
    }

    @JsonIgnore @Getter(onMethod = @__( @JsonIgnore ))
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "exchangeId", referencedColumnName = "exchangeId",
            insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    private Exchange exchange;

    @Setter
    @OneToMany(mappedBy = "orderBook", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<Order> orders = new ArrayList<>();

    public boolean matchOrder(Order order) {
        boolean madeTrade = false;
        Order matchedOrder = null;
        do {
            matchedOrder = getMatchingOrder(order);
            if (matchedOrder != null) {
                Trade trade = Trade.of(order, matchedOrder);
                order.addTrade(trade);
                matchedOrder.addTrade(trade);
                madeTrade = true;
            }
        }while(order.isOrderActive() && matchedOrder != null);
        return madeTrade;
    }

    public Order getMatchingOrder(Order order) {
        Order matchedOrder;
        if (order.getOrderType() == Order.OrderType.SELL) {
            matchedOrder = orders.stream()
                    .filter(o -> o.getOrderType() == Order.OrderType.BUY)
                    .filter(o -> o.isOrderActive())
                    .max(Order.highestPriceComparator.thenComparing(Order.earliestTimestampComparator))
                    .orElse(null);
            if (matchedOrder != null && order.getPrice() > matchedOrder.getPrice()) {
                matchedOrder = null;
            }
        }
        else {
            matchedOrder = orders.stream()
                    .filter(o -> o.getOrderType() == Order.OrderType.SELL)
                    .filter(o -> o.isOrderActive())
                    .max(Order.lowestPriceComparator.thenComparing(Order.earliestTimestampComparator))
                    .orElse(null);
            if (matchedOrder != null && order.getPrice() < matchedOrder.getPrice()) {
                matchedOrder = null;
            }
        }
        return matchedOrder;
    }


}
