package com.example.fuckallgpt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "checkout")
@Data
public class CheckOutConfig {
    private String successUrl;
    private String cancelUrl;
    private String brandName;
}
