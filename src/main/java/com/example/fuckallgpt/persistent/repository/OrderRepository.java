package com.example.fuckallgpt.persistent.repository;

import com.example.fuckallgpt.persistent.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderId(String id);
}
