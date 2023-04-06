package com.tradeteam.services;

import com.tradeteam.dtos.TradeDTO;
import com.tradeteam.entities.Trade;
import com.tradeteam.entities.WalletItem;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface TradingEngineTradeService {
    public List<Trade> getTrades(int userId);
    public HashMap<String, List<String>> getExchangeIdsAndCompanyAbbrevs();
    public List<WalletItem> getWalletByUserId(int userId);
}
