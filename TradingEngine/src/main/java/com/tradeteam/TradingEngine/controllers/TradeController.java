package com.tradeteam.TradingEngine.controllers;

import com.tradeteam.TradingEngine.entities.Trade;
import com.tradeteam.TradingEngine.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trades")
public class TradeController {

    @Autowired
    TradeService tradeService;

    @PostMapping("/byTradeId")
    public Trade getTradeDetails(@RequestBody int tradeId) {
        return tradeService.getTradeDetails(tradeId);
    }

    @GetMapping("/byUserId/{userId}")
    public List<Trade> getTradesByUserId(@PathVariable int userId) {
        return tradeService.getTradesByUserId(userId);
    }

}
