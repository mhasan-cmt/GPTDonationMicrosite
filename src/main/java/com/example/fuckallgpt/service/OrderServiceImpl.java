package com.example.fuckallgpt.service;

import com.example.fuckallgpt.config.DonationPricingConfig;
import com.example.fuckallgpt.enumerated.DonationType;
import com.example.fuckallgpt.exceptions.UnExpectedDonationType;
import com.example.fuckallgpt.persistent.entity.Order;
import com.example.fuckallgpt.exceptions.NoOrderFoundException;
import com.example.fuckallgpt.persistent.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final DonationPricingConfig donationPricingConfig;
    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findByOrderId(String id) {
        return orderRepository.findByOrderId(id).orElseThrow(NoOrderFoundException::new);
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
}
