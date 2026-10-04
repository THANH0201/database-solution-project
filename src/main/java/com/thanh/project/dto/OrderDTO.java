package com.thanh.project.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {

    @NotNull(message = "Customer ID is required")
    private Integer customerId;

    @NotNull(message = "Shipping address ID is required")
    private Integer shippingAddressId;

    private String status;

    private LocalDateTime deliveryDate;

    @Valid
    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderItemDTO> items;

    @Valid
    private List<PaymentRequestDTO> payments;

    @Data
    public static class OrderItemDTO {

        @NotNull(message = "Product ID is required")
        private Integer productId;

        @NotNull(message = "Quantity is required")
        @Min(value = 1, message = "Quantity must be greater than 0")
        private Integer quantity;
    }
}
