package com.tradeteam.TradingEngine.repositories;

import com.tradeteam.TradingEngine.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryViewOnly extends JpaRepository<Order, Integer> {

}
