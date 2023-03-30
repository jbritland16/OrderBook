package com.tradeteam.TradingEngine.repositories;

import com.tradeteam.TradingEngine.entities.OrderBook;
import com.tradeteam.TradingEngine.entities.OrderBookId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderBookRepository extends JpaRepository<OrderBook, OrderBookId> {

    public OrderBook findByOrderBookId(OrderBookId orderBookId);

    @Query(value = "SELECT company_abbrev FROM order_book WHERE exchange_id = :exchangeId", nativeQuery = true)
    public List<String> getAllCompanyAbbrevsByExchangeId(@Param("exchangeId") String exchangeId);

}
