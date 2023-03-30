package com.tradeteam.TradingEngine.services;

import com.tradeteam.TradingEngine.entities.Exchange;
import com.tradeteam.TradingEngine.entities.Trade;
import com.tradeteam.TradingEngine.repositories.ExchangeRepository;
import com.tradeteam.TradingEngine.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeViewerServiceImpl implements TradeViewerService {

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


}
