package com.tradeteam.TradingEngine.repositories;

import com.tradeteam.TradingEngine.entities.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, String> {

    public Exchange findByExchangeId(String exchangeId);

}
