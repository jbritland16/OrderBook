package com.tradeteam.dtos;

import com.tradeteam.entities.Order;
import com.tradeteam.entities.Trade;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class TradeDTO {

    private int tradeId;
    private ReceiveOrderDTO order1;
    private ReceiveOrderDTO order2;
    private LocalDateTime tradeTimestamp;
    private int numberTraded;
    private double pricePerShare;

    public TradeDTO(int tradeId, ReceiveOrderDTO order1,
                    ReceiveOrderDTO order2, LocalDateTime tradeTimestamp,
                    int numberTraded, double pricePerShare) {
        this.tradeId = tradeId;
        this.order1 = order1;
        this.order2 = order2;
        this.tradeTimestamp = tradeTimestamp;
        this.numberTraded = numberTraded;
        this.pricePerShare = pricePerShare;
    }

    public Trade tradeForUser(int userId) {
        Order connectedOrder = Stream.of(order1, order2)
                .filter(o -> o.getUserId() == userId)
                .findFirst().get()
                .order();
        return new Trade(tradeId, tradeTimestamp, numberTraded,
                pricePerShare, connectedOrder);
    }

}
