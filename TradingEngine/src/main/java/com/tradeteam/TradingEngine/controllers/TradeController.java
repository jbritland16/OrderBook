package com.tradeteam.TradingEngine.controllers;

import com.tradeteam.TradingEngine.entities.Trade;
import com.tradeteam.TradingEngine.services.TradeViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/trades")
public class TradeController {

    @Autowired
    TradeViewerService tradeViewerService;

    @PostMapping("/byTradeId")
    public Trade getTradeDetails(@RequestBody int tradeId) {
        return tradeViewerService.getTradeDetails(tradeId);
    }

    @PostMapping("/byUserId")
    public List<Trade> getTradesByUserId(@RequestBody int userId) {
        return tradeViewerService.getTradesByUserId(userId);
    }

    @GetMapping("/totalDailyValueForExchange/{exchangeId}/{date}")
    public double getTotalValueTradedByExchangeByDate(@PathVariable String exchangeId,
                                                      @PathVariable LocalDate date) {
        return tradeViewerService.getTotalValueTradedByExchangeByDate(exchangeId, date);
    }
}
