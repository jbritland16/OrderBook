package com.tradeteam.services;

import com.tradeteam.entities.OrderBook;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ExchangeOrderBookService {

    public List<String> getAllExchangeIds();
    public List<String> getCompanyAbbrevsByExchangeId(String exchangeId);
    public OrderBook getOrderBookByExchangeIdCompanyAbbrev(String exchangeId,
                                                           String companyAbbrev);

}
