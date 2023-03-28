package com.tradeteam.TradingEngine.repositories;

import com.tradeteam.TradingEngine.entities.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {

    public Trade findByTradeId(int tradeId);
    public List<Trade> findByExchangeExchangeId(String exchangeId);
    public List<Trade> findByOrder1UserId(int userId);
    public List<Trade> findByOrder2UserId(int userId);



}
