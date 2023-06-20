package com.example.fuckallgpt.persistent.repository;

import com.example.fuckallgpt.enumerated.DonationType;
import com.example.fuckallgpt.persistent.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderId(String id);
    @Query(value = "SELECT * FROM orders o WHERE o.order_status ='APPROVED' AND donation_type = ?1", nativeQuery = true)
    List<Order> findAllByDonationTypeAndOrderStatusApproved(String donationType);

    Optional<Order> findByDonorName(String name);
}
