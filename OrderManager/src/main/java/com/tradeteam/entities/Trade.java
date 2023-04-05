package com.tradeteam.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @AllArgsConstructor @NoArgsConstructor @ToString
public class Trade {

    private int tradeId;
    private LocalDateTime tradeTimestamp;
    private int numberTraded;
    private double pricePerShare;
    private Order connectedOrder;

}
