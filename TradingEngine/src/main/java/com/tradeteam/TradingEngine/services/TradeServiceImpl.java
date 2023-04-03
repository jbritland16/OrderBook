package com.tradeteam.TradingEngine.services;

import com.tradeteam.TradingEngine.entities.Trade;
import com.tradeteam.TradingEngine.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Trade> trades = tradeRepository.findByOrdersUserId(userId);
        for (Trade trade : trades) {
            trade.setOrders(trade.getOrders().stream()
                    .filter(o -> o.getUserId() == userId).collect(Collectors.toList()));
        }
        return trades;
    }

    @Override
    public double getTotalValueTradedByExchangeByDate(String exchangeId, int year,
                                                      int month, int day) {
        List<Trade> trades = tradeRepository.findByOrdersOrderBookExchangeExchangeId(exchangeId);
        return trades.stream()
                .filter(t -> t.getTradeTimestamp().getYear() == year)
                .filter(t -> t.getTradeTimestamp().getMonthValue() == month)
                .filter(t -> t.getTradeTimestamp().getDayOfMonth() == day)
                .map(t -> t.getNumberTraded() * t.getPricePerShare().doubleValue())
                .reduce(0.0, Double::sum);
    }


}
