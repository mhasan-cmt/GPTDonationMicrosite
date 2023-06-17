package com.example.fuckallgpt.entity;

import com.example.fuckallgpt.enumerated.DonationType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "approve_url")
    private String approveUrl;
    @Column(name = "donation_type")
    private DonationType donationType;
    @Column(name = "created_at")
    private LocalDate createdAt;
}
