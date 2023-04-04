package com.tradeteam.TradingEngine.repositories;

import com.tradeteam.TradingEngine.entities.Order;
import jakarta.persistence.QueryHint;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    public List<Order> findByUserId(int userId);
    public Order findByOrderId(int orderId);


}
