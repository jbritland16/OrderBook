package com.tradeteam.TradingEngine.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @AllArgsConstructor @NoArgsConstructor @ToString
public class Exchange {

    @Id
    @NonNull
    private String exchangeId;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "exchange")
    private List<OrderBook> orderBooks = new ArrayList<>();

    @NonNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "exchange")
    private List<Trade> trades = new ArrayList<>();

    // private double feeLadder; This was included in Kishore's example but I would like
    // to do more research before implementing

}
