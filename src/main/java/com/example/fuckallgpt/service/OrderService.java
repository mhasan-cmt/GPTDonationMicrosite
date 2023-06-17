package com.example.fuckallgpt.service;

import com.example.fuckallgpt.enumerated.DonationType;
import com.example.fuckallgpt.persistent.entity.Order;

public interface OrderService {
    Order saveOrder(Order order);
    Order findByOrderId(String id);
    String getPricesByDonationType(DonationType donationType);
}
