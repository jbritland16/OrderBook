package com.tradeteam.TradingEngine.entities;

import jakarta.persistence.EmbeddedId;

public class OrderBook {

    @EmbeddedId
    private OrderBookId orderBookId;

    private String companyName;

}
