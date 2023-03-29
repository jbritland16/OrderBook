package com.tradeteam.TradingEngine.controllers;

import com.tradeteam.TradingEngine.entities.Trade;
import com.tradeteam.TradingEngine.services.TradeViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("trades")
public class TradeController {

    @Autowired
    TradeViewerService tradeViewerService;

    @GetMapping("/byId")
    public Trade getTradeDetails(int tradeId) {
        return tradeViewerService.getTradeDetails(tradeId);
    }

    @GetMapping("/byUser")
    public List<Trade> getTradesByUserId(int userId) {
        return tradeViewerService.getTradesByUserId(userId);
    }


}
