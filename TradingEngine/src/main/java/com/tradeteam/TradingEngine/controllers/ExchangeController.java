package com.tradeteam.TradingEngine.controllers;

import com.tradeteam.TradingEngine.entities.Order;
import com.tradeteam.TradingEngine.entities.OrderBook;
import com.tradeteam.TradingEngine.entities.OrderBookId;
import com.tradeteam.TradingEngine.services.ExchangeService;
import com.tradeteam.TradingEngine.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public OrderBook getOrderBookCurrentOrdersByOrderBookId(@PathVariable String exchangeId,
                                               @PathVariable String companyAbbrev) {
        return exchangeService.getOrderBookCurrentOrdersByOrderBookId(
                new OrderBookId(exchangeId, companyAbbrev));
    }

    @GetMapping("/{exchangeId}/dailyTradedValue/{year}/{month}/{day}")
    public double getTotalValueTradedByExchangeByDate(@PathVariable String exchangeId,
                                                      @PathVariable int year,
                                                      @PathVariable int month,
                                                      @PathVariable int day) {
        return tradeService.getTotalValueTradedByExchangeByDate(exchangeId, year, month, day);
    }

    @GetMapping("/{exchangeId}/{companyAbbrev}/bestBuyAndSellPrice")
    public Double[] getBestBuyAndSellPriceByOrderBookId(
            @PathVariable String exchangeId,
            @PathVariable String companyAbbrev) {
        return exchangeService.getBestBuyAndSellPricesByOrderBookId(
                new OrderBookId(exchangeId, companyAbbrev));
    }

}
