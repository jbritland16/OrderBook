package com.tradeteam.services;

import com.tradeteam.consumers.TradingEngineApiConsumer;
import com.tradeteam.entities.OrderBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
