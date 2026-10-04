package com.thanh.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponseDTO {

    private Integer id;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String status;

    private CustomerDTO customer;
    private AddressDTO shippingAddress;
    private List<ItemDTO> items;
    private List<PaymentRequestDTO> payments;
    private BigDecimal totalPrice;

    @Data
    public static class CustomerDTO {
        private Integer id;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
    }

    @Data
    public static class AddressDTO {
        private Integer id;
        private String streetAddress;
        private String postalCode;
        private String city;
        private String country;
    }

    @Data
    public static class ItemDTO {
        private Integer productId;
        private String productName;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal lineTotal;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PaymentRequestDTO{
        private String paymentType;
        private Double amount;
        // Card
        private String cardNumber;
        private String cardHolder;
        private LocalDate expiryDate;
        // Voucher
        private String voucherCode;
        private Double discountAmount;
    }
}
