package com.tradeteam.dtos;

import com.tradeteam.entities.Order;
import com.tradeteam.entities.Trade;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public class TradeDTO {

    private int tradeId;
    private List<ReceiveOrderDTO> orders;
    private LocalDateTime tradeTimestamp;
    private int numberTraded;
    private double pricePerShare;

    public TradeDTO(int tradeId, List<ReceiveOrderDTO> orders, LocalDateTime tradeTimestamp,
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
