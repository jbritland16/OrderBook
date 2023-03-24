package com.tradeteam.TradingEngine.repositories;

import com.tradeteam.TradingEngine.entities.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Integer> {



}
