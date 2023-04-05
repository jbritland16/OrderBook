package com.tradeteam.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tradeteam.entities.Order;
import com.tradeteam.entities.OrderBook;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderBookDTO {

    private OrderBookId orderBookId;
    private String companyName;
    private List<Order> orders;

    public OrderBookDTO(OrderBookId orderBookId, String companyName,
                        List<ExistingOrderDTO> orders) {
        this.orderBookId = orderBookId;
        this.companyName = companyName;
        this.orders = orders.stream()
                .filter(odto -> odto.isOrderActive())
                .map(odto -> odto.order())
                .collect(Collectors.toList());
    }

    public OrderBookDTO(OrderBookId orderBookId) {
        this.orderBookId = orderBookId;
    }

    public OrderBook orderBook() {
        List<Order> buyOrders = this.orders.stream()
                .filter(o -> o.getOrderType() == Order.OrderType.BUY)
                .sorted(Comparator.comparingDouble(Order::getPrice).reversed())
                .collect(Collectors.toList());
        List<Order> sellOrders = this.orders.stream()
                .filter(o -> o.getOrderType() == Order.OrderType.SELL)
                .sorted(Comparator.comparingDouble(Order::getPrice))
                .collect(Collectors.toList());
        return new OrderBook(orderBookId.getCompanyAbbrev(),
                orderBookId.getExchangeId(),
                companyName, buyOrders, sellOrders);
    }

}
