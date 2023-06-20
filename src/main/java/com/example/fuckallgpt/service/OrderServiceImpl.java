package com.example.fuckallgpt.service;

import com.example.fuckallgpt.config.DonationPricingConfig;
import com.example.fuckallgpt.enumerated.DonationType;
import com.example.fuckallgpt.exceptions.UnExpectedDonationType;
import com.example.fuckallgpt.persistent.entity.Order;
import com.example.fuckallgpt.persistent.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final DonationPricingConfig donationPricingConfig;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findByOrderId(String id) {
        return orderRepository.findByOrderId(id).orElse(null);
    }

    @Override
    public String getPricesByDonationType(DonationType donationType) {
        String price;
        switch (donationType) {
            case PRIVATE -> price = donationPricingConfig.getPrivatee();
            case SERGEANT -> price = donationPricingConfig.getSergeant();
            case CAPTAIN -> price = donationPricingConfig.getCaptain();
            case GENERAL -> price = donationPricingConfig.getGeneral();
            default -> {
                throw new UnExpectedDonationType();
            }
        }
        return price;
    }

    @Override
    public Order getOrderByDonorName(String donorName) {
        return orderRepository.findByDonorName(donorName).orElse(null);
    }

    @Override
    public List<Order> getApprovedOrdersByDonationType(DonationType donationType) {
        return orderRepository.findAllByDonationTypeAndOrderStatusApproved(donationType.toString());
    }
}
