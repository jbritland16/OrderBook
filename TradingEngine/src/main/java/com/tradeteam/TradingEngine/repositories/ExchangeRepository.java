package com.tradeteam.TradingEngine.repositories;

import com.tradeteam.TradingEngine.entities.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, String> {

    public Exchange findByExchangeId(String exchangeId);
    @Query(value = "SELECT exchange_id FROM exchange", nativeQuery = true)
    public List<String> getAllExchangeIds();

}
