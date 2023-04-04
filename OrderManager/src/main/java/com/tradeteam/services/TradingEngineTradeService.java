package com.tradeteam.services;

import com.tradeteam.dtos.TradeDTO;
import com.tradeteam.entities.Trade;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface TradingEngineTradeService {
    public List<TradeDTO> getTrades(int userId);
    public HashMap<String, List<String>> getExchangeIdsAndCompanyAbbrevs();
}
