package com.tradeteam.consumers;

import com.tradeteam.dtos.*;
import com.tradeteam.entities.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("trading-engine")
public interface TradingEngineApiConsumer {

    @GetMapping("exchanges/orderBook")
    public OrderBookDTO getOrderBookByOrderBookId(@RequestParam OrderBookId orderBookId);

    @PostMapping("exchanges/addOrder")
    public void addOrderToExchange(@RequestParam NewOrderDTO order);

    @GetMapping("orders/byUser")
    public List<ReceiveOrderDTO> getOrdersByUserId(@RequestParam int userId);

    @GetMapping("orders/byId")
    public ReceiveOrderDTO getOrderById(@RequestParam int orderId);

    @GetMapping
    public TradeDTO getTradeDetails(@RequestParam int tradeId);

    @GetMapping
    public List<TradeDTO> getTradesByUser(@RequestParam int userId);

}
