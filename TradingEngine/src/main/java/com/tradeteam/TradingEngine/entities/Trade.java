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
    private int tradeId;

    @NonNull
    @ManyToMany(mappedBy = "orderId")
    private List<Order> orders;

    @NonNull
    private LocalDateTime tradeTimestamp;


}
