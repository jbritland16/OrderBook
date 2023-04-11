package com.tradeteam.consumers;

import com.tradeteam.dtos.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "trading-engine")
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

    @GetMapping("/exchanges/{exchangeId}/{companyAbbrev}/bestBuyAndSellPrice")
    public Double[] getBestBuyAndSellPriceByOrderBookId(@PathVariable String exchangeId,
                                                        @PathVariable String companyAbbrev);

    @PostMapping("/orders/byUserId")
    public List<ExistingOrderDTO> getOrdersByUserId(int userId);

    @PostMapping("/orders/byOrderId")
    public ExistingOrderDTO getOrderById(int orderId);

    @PostMapping("/orders/{exchangeId}/{companyAbbrev}/new")
    public ExistingOrderDTO addNewOrder(NewOrderDTO order,
                                        @PathVariable String exchangeId,
                                        @PathVariable String companyAbbrev);

    @PostMapping("/orders/wallet")
    public Map<String, Integer> getWalletByUserId(int userId);

    @PostMapping("/orders/cancel")
    public ExistingOrderDTO cancelOrder(int[] orderIdUserId);

    @PostMapping("/orders/update")
    public ExistingOrderDTO updateOrder(ExistingOrderDTO orderDTO);

    @GetMapping("/trades/byTradeId")
    public TradeDTO getTradeDetails(int tradeId);

    @GetMapping("/trades/byUserId/{userId}")
    public List<TradeDTO> getTradesByUser(@PathVariable int userId);

}
