package com.example.fuckallgpt.paypal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseUnit {
    private MoneyDTO amount;
}
