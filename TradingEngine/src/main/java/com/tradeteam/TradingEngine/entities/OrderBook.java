package com.tradeteam.TradingEngine.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        matchedOrder = this.orders.stream().filter(o -> o.getOrderType() != order.getOrderType())
                .filter(o -> o.isOrderActive())
                .sorted(Order::compareTo)
                .findFirst().orElse(null);
        if (matchedOrder != null && !order.confirmTradeWithOrder(matchedOrder)) {
            matchedOrder = null;
        }
        return matchedOrder;
    }


}
