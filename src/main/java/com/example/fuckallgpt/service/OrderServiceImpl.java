package com.example.fuckallgpt.service;

import com.example.fuckallgpt.persistent.entity.Order;
import com.example.fuckallgpt.exceptions.NoOrderFoundException;
import com.example.fuckallgpt.persistent.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findByOrderId(String id) {
        return orderRepository.findByOrderId(id).orElseThrow(NoOrderFoundException::new);
    }
}
