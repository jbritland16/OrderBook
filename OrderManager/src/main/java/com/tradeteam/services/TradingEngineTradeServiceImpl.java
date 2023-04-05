package com.tradeteam.services;

import com.tradeteam.consumers.TradingEngineApiConsumer;
import com.tradeteam.dtos.TradeDTO;
import com.tradeteam.entities.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradingEngineTradeServiceImpl implements TradingEngineTradeService {
    @Autowired
    TradingEngineApiConsumer apiConsumer;
    @Override
    public List<Trade> getTrades(int userId) {
        return apiConsumer.getTradesByUser(userId).stream()
                .map(tdto -> tdto.tradeForUser(userId))
                .collect(Collectors.toList());
    }

    @Override
    public HashMap<String, List<String>> getExchangeIdsAndCompanyAbbrevs() {
        HashMap<String, List<String>> exchangeCompanyAbbrevs = new HashMap<>();
        List<String> exchangeIds = apiConsumer.getAllExchangeIds();
        for(String exchangeId : exchangeIds) {
            List<String> companyAbbrevs = apiConsumer.getAllCompanyAbbrevsByExchangeId(exchangeId);
            exchangeCompanyAbbrevs.put(exchangeId, companyAbbrevs);
        }
        return exchangeCompanyAbbrevs;
    }
}
