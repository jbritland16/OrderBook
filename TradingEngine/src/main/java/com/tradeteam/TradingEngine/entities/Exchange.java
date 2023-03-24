package com.tradeteam.TradingEngine.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @AllArgsConstructor @NoArgsConstructor @ToString @RequiredArgsConstructor
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String exchangeId;

    @NonNull
    @OneToMany(mappedBy = "exchangeId", cascade = CascadeType.ALL)
    private List<OrderBook> orderBooks;

    // private double feeLadder; This was included in Kishore's example but I would like
    // to do more research before implementing

}
