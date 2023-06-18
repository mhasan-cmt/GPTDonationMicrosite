package com.example.fuckallgpt.service;

import com.example.fuckallgpt.enumerated.DonationType;
import com.example.fuckallgpt.persistent.entity.Order;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Order saveOrder(Order order);
    Order findByOrderId(String id);
    String getPricesByDonationType(DonationType donationType);
    List<Order> getApprovedOrdersByDonationType(DonationType donationType);
}
