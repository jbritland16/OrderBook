package com.tradeteam.TradingEngine.controllers;

import com.tradeteam.TradingEngine.entities.OrderBook;
import com.tradeteam.TradingEngine.entities.OrderBookId;
import com.tradeteam.TradingEngine.services.ExchangeService;
import com.tradeteam.TradingEngine.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/exchanges")
public class ExchangeController {

    @Autowired
    ExchangeService exchangeService;

    @Autowired
    TradeService tradeService;

    @GetMapping("/allExchangeIds")
    public List<String> getAllExchangeIds() {
        return exchangeService.getAllExchangeIds();
    }

    @GetMapping("/{exchangeId}/allCompanyAbbrevs")
    public List<String> getAllCompanyAbbrevsByExchangeId(@PathVariable String exchangeId) {
        return exchangeService.getAllCompanyAbbrevsByExchangeId(exchangeId);
    }

    @GetMapping("/{exchangeId}/{companyAbbrev}")
    public OrderBook getOrderBookByOrderBookId(@PathVariable String exchangeId,
                                               @PathVariable String companyAbbrev) {
        return exchangeService.getOrderBookByOrderBookId(
                new OrderBookId(exchangeId, companyAbbrev));
    }

    @GetMapping("/{exchangeId}/dailyTradedValue/{date}")
    public double getTotalValueTradedByExchangeByDate(@PathVariable String exchangeId,
                                                      @PathVariable LocalDate date) {
        return tradeService.getTotalValueTradedByExchangeByDate(exchangeId, date);
    }

}
