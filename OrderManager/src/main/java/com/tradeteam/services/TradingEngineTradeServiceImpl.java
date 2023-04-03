package com.tradeteam.services;

import com.tradeteam.consumers.TradingEngineApiConsumer;
import com.tradeteam.dtos.TradeDTO;
import com.tradeteam.entities.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TradingEngineTradeServiceImpl implements TradingEngineTradeService {
    @Autowired
    TradingEngineApiConsumer apiConsumer;
    @Override
    public List<TradeDTO> getTrades(int userId) {
        return apiConsumer.getTradesByUser(userId);
    }
}
