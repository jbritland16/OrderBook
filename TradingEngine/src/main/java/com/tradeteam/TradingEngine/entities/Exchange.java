package com.tradeteam.TradingEngine.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Exchange {

    @Id
    @NonNull
    private String exchangeId;


    @JsonIgnore @Getter(onMethod = @__( @JsonIgnore )) @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "exchange")
    @EqualsAndHashCode.Exclude
    private List<OrderBook> orderBooks = new ArrayList<>();

    // private double feeLadder; This was included in Kishore's example but I would like
    // to do more research before implementing

    public OrderBook getOrderBookByCompanyAbbrev(String companyAbbrev) {
        return orderBooks.stream()
                .filter(o -> o.getOrderBookId().getCompanyAbbrev() == companyAbbrev)
                .findFirst().get();
    }

    public double getTotalTradedValueByDate(LocalDate date) {
        return orderBooks.stream()
                .map(ob -> ob.getOrders())
                .flatMap(Collection::stream)
                .map(o -> o.getTrades())
                .flatMap(Collection::stream)
                .filter(t -> t.getTradeTimestamp().getYear() == date.getYear())
                .filter(t -> t.getTradeTimestamp().getDayOfYear() == date.getDayOfYear())
                .distinct()
                .mapToDouble(t -> (t.getNumberTraded() * t.getPricePerShare()))
                .sum();
    }

}
