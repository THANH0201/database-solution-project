package com.thanh.project.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "payment_inheritance")
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class PaymentInheritance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String paymentType; // card, voucher

    @Column(nullable = false)
    private LocalDateTime paymentDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore 
    private Order order;
}

