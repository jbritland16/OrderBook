package com.tradeteam.services;

import com.tradeteam.consumers.TradingEngineApiConsumer;
import com.tradeteam.dtos.NewOrderDTO;
import com.tradeteam.dtos.ExistingOrderDTO;
import com.tradeteam.entities.Order;
import com.tradeteam.entities.Trade;
import com.tradeteam.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TradingEngineApiConsumer tradingEngineApiConsumer;

//        public List<Trade> getTradeHistory(int userId) {
//            List<Order> userOrders = orderRepository.findByUserId(userId);
//            List<Trade> trades = new ArrayList<>();
//            for (Trade trade : trades) {
//                for (Order order : userOrders) {
//                        trades.add(trade);
//                        break;
//                    }
//                }
//            }
//            return userTrades;
//        }
//    }


    public List<Order> findByUserId(int userId) {
        return tradingEngineApiConsumer.getOrdersByUserId(userId)
                .stream().map(odto -> odto.order())
                .collect(Collectors.toList());
    }

    @Override
    public Order createOrder(Order order) {
        return tradingEngineApiConsumer.addNewOrder(
                NewOrderDTO.of(order), order.getExchangeId(), order.getCompanyAbbrev())
                .order();
    }

    @Override
    public Order cancelOrder(int orderId, int userId) {
        return tradingEngineApiConsumer.cancelOrder(new int[] {orderId, userId}).order();
    }

    @Override
    public Order findById(int orderId) {
        return tradingEngineApiConsumer.getOrderById(orderId).order();
    }

    @Override
    public Order updateOrder(int orderId, int numberOrdered, double price,
                             String orderType, String companyAbbrev) {
        ExistingOrderDTO orderDTO = tradingEngineApiConsumer.getOrderById(orderId);
        orderDTO.setNumberOrdered(numberOrdered);
        orderDTO.setPrice(price);
        orderDTO.setOrderType(Order.OrderType.valueOf(orderType));
        orderDTO.setCompanyAbbrev(companyAbbrev);
        return tradingEngineApiConsumer.updateOrder(orderDTO).order();
    }


}


