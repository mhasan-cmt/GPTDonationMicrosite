package com.example.fuckallgpt.paypal.dto;

import com.example.fuckallgpt.enumerated.DonationType;

public record OrderRequest(DonationType donationType, String donorsName) {
}
