package com.example.fuckallgpt.service;

import com.example.fuckallgpt.entity.Order;

public interface OrderService {
    Order saveOrder(Order order);
    Order findByOrderId(String id);

}
