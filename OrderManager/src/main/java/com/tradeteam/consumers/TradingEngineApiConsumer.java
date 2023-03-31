package com.tradeteam.consumers;

import com.tradeteam.dtos.*;
import com.tradeteam.entities.Order;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@FeignClient("trading-engine")
public interface TradingEngineApiConsumer {

    @GetMapping("/exchanges/allExchangeIds")
    public List<String> getAllExchangeIds();

    @GetMapping("/exchanges/{exchangeId}/allCompanyAbbrevs")
    public List<String> getAllCompanyAbbrevsByExchangeId(@PathVariable String exchangeId);

    @GetMapping("/exchanges/{exchangeId}/{companyAbbrev}")
    public OrderBookDTO getOrderBookByOrderBookId(@PathVariable String exchangeId,
                                                  @PathVariable String companyAbbrev);

    @GetMapping("/exchanges/{exchangeId}/dailyTradedValue/{year}/{month}/{day}")
    public double getTotalValueTradedByExchangeByDate(@PathVariable String exchangeId,
                                                      @PathVariable int year,
                                                      @PathVariable int month,
                                                      @PathVariable int day);

    @PostMapping("/orders/byUserId")
    public List<ReceiveOrderDTO> getOrdersByUserId(@RequestParam int userId);

    @PostMapping("/orders/byOrderId")
    public ReceiveOrderDTO getOrderById(@RequestParam int orderId);

    @PostMapping("/orders/new")
    public void addNewOrder(@RequestParam NewOrderDTO order);

    @PostMapping("/orders/wallet")
    public Map<String, Integer> getWalletByUserId(@RequestParam int userId);

    @GetMapping("/trades/byTradeId")
    public TradeDTO getTradeDetails(@RequestParam int tradeId);

    @GetMapping("/trades/byUserId")
    public List<TradeDTO> getTradesByUser(@RequestParam int userId);

}
