package com.tradeteam.services;

import com.tradeteam.consumers.TradingEngineApiConsumer;
import com.tradeteam.dtos.OrderBookId;
import com.tradeteam.entities.OrderBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeOrderBookServiceImpl implements ExchangeOrderBookService {

    @Autowired
    TradingEngineApiConsumer apiConsumer;

    @Override
    public List<String> getAllExchangeIds() {
        return apiConsumer.getAllExchangeIds();
    }

    @Override
    public List<String> getCompanyAbbrevsByExchangeId(String exchangeId) {
        return apiConsumer.getAllCompanyAbbrevsByExchangeId(exchangeId);
    }

    @Override
    public OrderBook getOrderBookByExchangeIdCompanyAbbrev(String exchangeId, String companyAbbrev) {
        return apiConsumer.getOrderBookByOrderBookId(exchangeId, companyAbbrev).orderBook();
    }

    @Override
    public Map<String, Map<String, Double[]>> getBestBuyAndSellPrices() {
        Map<String, Map<String, Double[]>> exchangeMap = new HashMap<>();
        for (String exchange:apiConsumer.getAllExchangeIds()) {
            Map<String, Double[]> companyMap = new HashMap<>();
            for (String company:apiConsumer.getAllCompanyAbbrevsByExchangeId(exchange)) {
                companyMap.put(company,
                        apiConsumer.getBestBuyAndSellPriceByOrderBookId(exchange, company));
            }
            exchangeMap.put(exchange, companyMap);
        }
        return exchangeMap;
    }
}
