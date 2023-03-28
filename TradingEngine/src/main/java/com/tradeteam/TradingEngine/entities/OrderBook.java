package com.tradeteam.TradingEngine.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Comparator;
import java.util.List;

@Entity
@Getter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode @RequiredArgsConstructor
public class OrderBook {

    @EmbeddedId
    private OrderBookId orderBookId;

    @NonNull
    private String companyName;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exchangeId", referencedColumnName = "exchangeId",
            insertable = false, updatable = false)
    private Exchange exchange;

    @NonNull
    @OneToMany(mappedBy = "orderBook")
    private List<Order> orders;

    public void addOrder(Order order) {
        Order matchedOrder = null;
        do {
            matchedOrder = getMatchingOrder(order);
            if (matchedOrder != null) {
                Trade trade = Trade.of(order, matchedOrder);
                order.addTrade(trade);
                matchedOrder.addTrade(trade);
            }
        }while(order.isOrderActive() && matchedOrder != null);
        orders.add(order);
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
