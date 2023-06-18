package com.example.fuckallgpt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "donations")
@Data
public class DonationPricingConfig {
    private String privatee;
    private String sergeant;
    private String captain;
    private String general;
}
