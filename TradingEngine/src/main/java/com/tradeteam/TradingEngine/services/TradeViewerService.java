package com.tradeteam.TradingEngine.services;

import com.tradeteam.TradingEngine.entities.Trade;

import java.util.List;

public interface TradeViewerService {

    public Trade getTradeDetails(int tradeId);
    public List<Trade> getTradesByUserId(int userId);
    public List<Trade> getTradesByExchangeId(String exchangeId);

}
