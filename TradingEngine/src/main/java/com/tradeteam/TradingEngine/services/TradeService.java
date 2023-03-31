package com.tradeteam.TradingEngine.services;

import com.tradeteam.TradingEngine.entities.Trade;

import java.time.LocalDate;
import java.util.List;

public interface TradeService {

    public Trade getTradeDetails(int tradeId);
    public List<Trade> getTradesByUserId(int userId);
    public double getTotalValueTradedByExchangeByDate(String exchangeId, int year,
                                                      int month, int day);

}
