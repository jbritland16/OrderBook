package com.tradeteam.TradingEngine.repositories;

import com.tradeteam.TradingEngine.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    public List<Order> findByUserId(int userId);

}
