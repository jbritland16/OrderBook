package com.tradeteam.TradingEngine.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @NoArgsConstructor @AllArgsConstructor @ToString @RequiredArgsConstructor
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tradeId")
    private int tradeId;

    @NonNull
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private List<Order> orders;

    @NonNull
    private LocalDateTime tradeTimestamp;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "exchangeId", referencedColumnName = "exchangeId", insertable = false, updatable = false)
    private Exchange exchange;

}
