package com.tradeteam.services;

import com.tradeteam.dtos.OrderBookId;
import com.tradeteam.entities.OrderBook;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface ExchangeOrderBookService {

    public List<String> getAllExchangeIds();
    public List<String> getCompanyAbbrevsByExchangeId(String exchangeId);
    public OrderBook getOrderBookByExchangeIdCompanyAbbrev(String exchangeId,
                                                           String companyAbbrev);
    public Map<String, Map<String, Double[]>> getBestBuyAndSellPrices();

}
