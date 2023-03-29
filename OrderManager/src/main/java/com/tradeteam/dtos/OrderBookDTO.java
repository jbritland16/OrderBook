package com.tradeteam.dtos;

import com.tradeteam.entities.Order;
import com.tradeteam.entities.OrderBook;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class OrderBookDTO {

    private OrderBookId orderBookId;
    private String companyName;
    private List<Order> orders;

    public OrderBookDTO(OrderBookId orderBookId, String companyName,
                        List<ReceiveOrderDTO> orders) {
        this.orderBookId = orderBookId;
        this.companyName = companyName;
        this.orders = orders.stream()
                .peek(odto -> odto.setOrderBookId(orderBookId))
                .map(odto -> odto.order())
                .collect(Collectors.toList());
    }

    public OrderBookDTO(OrderBookId orderBookId) {
        this.orderBookId = orderBookId;
    }

    public OrderBook orderBook() {
        return new OrderBook(orderBookId.getCompanyAbbrev(),
                orderBookId.getExchangeId(),
                companyName, orders);
    }

}
