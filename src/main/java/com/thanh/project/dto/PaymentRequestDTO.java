package com.thanh.project.dto;

import lombok.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PaymentRequestDTO {
    private Integer id;
    @NotNull(message = "paymentType is required")
    private String paymentType;

    @NotNull(message = "amount is required")
    private Double amount;

    // CARD
    private String cardNumber;

    private String cardHolder;

    private LocalDate expiryDate;

    // VOUCHER
    private String voucherCode;

    private Double discountAmount;
}