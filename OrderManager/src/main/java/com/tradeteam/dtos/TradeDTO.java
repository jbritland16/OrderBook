package com.tradeteam.dtos;

import com.tradeteam.entities.Order;
import com.tradeteam.entities.Trade;

import java.time.LocalDateTime;
import java.util.List;

public class TradeDTO {

    private int tradeId;
    private List<ExistingOrderDTO> orders;
    private LocalDateTime tradeTimestamp;
    private int numberTraded;
    private double pricePerShare;

    public TradeDTO(int tradeId, List<ExistingOrderDTO> orders, LocalDateTime tradeTimestamp,
                    int numberTraded, double pricePerShare) {
        this.tradeId = tradeId;
        this.orders = orders;
        this.tradeTimestamp = tradeTimestamp;
        this.numberTraded = numberTraded;
        this.pricePerShare = pricePerShare;
    }

    public Trade tradeForUser(int userId) {
        Order connectedOrder = orders.get(0).order();
        return new Trade(tradeId, tradeTimestamp, numberTraded,
                pricePerShare, connectedOrder);
    }

}
