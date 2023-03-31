package com.tradeteam.TradingEngine.services;

import com.tradeteam.TradingEngine.entities.Trade;
import com.tradeteam.TradingEngine.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public Trade getTradeDetails(int tradeId) {
        return tradeRepository.findByTradeId(tradeId);
    }

    @Override
    public List<Trade> getTradesByUserId(int userId) {
        return tradeRepository.findByOrdersUserId(userId);
    }

    @Override
    public double getTotalValueTradedByExchangeByDate(String exchangeId, LocalDate date) {
        List<Trade> trades = tradeRepository.findByOrdersOrderBookExchangeExchangeId(exchangeId);
        return trades.stream()
                .filter(t -> t.getTradeTimestamp().toLocalDate() == date)
                .map(t -> t.getNumberTraded() * t.getPricePerShare())
                .reduce(0.0, Double::sum);
    }


}
