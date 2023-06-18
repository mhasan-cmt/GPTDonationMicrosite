package com.example.fuckallgpt.controller;

import com.example.fuckallgpt.enumerated.DonationType;
import com.example.fuckallgpt.persistent.entity.Order;
import com.example.fuckallgpt.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final OrderService orderService;
    @GetMapping
    public String getHomePage(Model model) {
        List<Order> privateDonors = orderService.getApprovedOrdersByDonationType(DonationType.PRIVATE);
        List<Order> sergeantDonors = orderService.getApprovedOrdersByDonationType(DonationType.SERGEANT);
        List<Order> captainDonors = orderService.getApprovedOrdersByDonationType(DonationType.CAPTAIN);
        List<Order> generalDonors = orderService.getApprovedOrdersByDonationType(DonationType.GENERAL);

        model.addAttribute("privateDonors", privateDonors);
        model.addAttribute("sergeantDonors", sergeantDonors);
        model.addAttribute("captainDonors", captainDonors);
        model.addAttribute("generalDonors", generalDonors);
        return "index";
    }
}
