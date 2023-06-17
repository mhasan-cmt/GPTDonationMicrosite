package com.example.fuckallgpt.controller;

import com.example.fuckallgpt.persistent.entity.Order;
import com.example.fuckallgpt.paypal.PayPalHttpClient;
import com.example.fuckallgpt.paypal.dto.*;
import com.example.fuckallgpt.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping(value = "/checkout")
@Slf4j
@RequiredArgsConstructor
public class CheckoutController {

    private final PayPalHttpClient payPalHttpClient;
    private final OrderService orderService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<OrderResponseDTO> checkout(@RequestBody OrderRequest orderRequest) throws Exception {
        var orderDTO = new OrderDTO();
        orderDTO.setIntent(OrderIntent.CAPTURE);
        orderDTO.setPurchaseUnits(List.of(new PurchaseUnit(MoneyDTO.builder()
                .currencyCode("USD")
                .value(orderService.getPricesByDonationType(orderRequest.donationType()))
                .build())));
        var appContext = new PayPalAppContextDTO();
        appContext.setReturnUrl("http://localhost:8088/checkout/success");
        appContext.setCancelUrl("http://localhost:8088/checkout/cancel");
        appContext.setBrandName("Fuck All GPT");
        appContext.setLandingPage(PaymentLandingPage.BILLING);
        orderDTO.setApplicationContext(appContext);
        var orderResponse = payPalHttpClient.createOrder(orderDTO);

        var entity = new Order();
        entity.setOrderId(orderResponse.getId());
        entity.setOrderStatus(orderResponse.getStatus().toString());
        entity.setCreatedAt(LocalDate.now());
        entity.setDonationType(orderRequest.donationType());
        orderResponse.getLinks().forEach(linkDTO -> {
            if (linkDTO.getRel().equals("approve")) {
                entity.setApproveUrl(linkDTO.getHref());
            }
        });

        var out = orderService.saveOrder(entity);
        log.info("Saved order: {}", out);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping(value = "/success")
    public String paymentSuccess(HttpServletRequest request, Model model) {
        var orderId = request.getParameter("token");
        var out = orderService.findByOrderId(orderId);
        out.setOrderStatus(OrderStatus.APPROVED.toString());
        orderService.saveOrder(out);
        log.info("Saved successful payment order: {}", out);
        return "index";
    }

    @GetMapping(value = "/cancel")
    public String paymentCancel(HttpServletRequest request, Model model) {
        var orderId = request.getParameter("token");
        var out = orderService.findByOrderId(orderId);
        out.setOrderStatus(OrderStatus.CANCELED.toString());
        orderService.saveOrder(out);
        log.info("Saved Canceled payment order: {}", out);
        return "index";
    }
}
