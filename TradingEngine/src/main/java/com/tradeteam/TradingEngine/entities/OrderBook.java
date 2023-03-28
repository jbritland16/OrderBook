package com.tradeteam.TradingEngine.entities;

import jakarta.persistence.*;

@Entity
public class OrderBook {

    @EmbeddedId
    private OrderBookId orderBookId;

    private String companyName;

    @ManyToOne
    @JoinColumn(name = "exchangeId", referencedColumnName = "exchangeId", insertable = false, updatable = false)
    private Exchange exchange;

}
