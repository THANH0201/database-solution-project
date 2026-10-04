package com.thanh.project.mapper;

import com.thanh.project.dto.OrderResponseDTO;
import com.thanh.project.entity.Order;
import java.util.*;
import java.math.BigDecimal;

public class OrderMapper {
    private OrderMapper() {
    }

    public static OrderResponseDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }

        OrderResponseDTO dto = new OrderResponseDTO();
        // =========================
        // Basic Order Info
        // =========================

        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setDeliveryDate(order.getDeliveryDate());
        dto.setStatus(order.getStatus());

        // =========================
        // Customer
        // =========================
        if (order.getCustomer() != null) {
            OrderResponseDTO.CustomerDTO customer = new OrderResponseDTO.CustomerDTO();
            customer.setId(order.getCustomer().getId());
            customer.setFirstName(order.getCustomer().getFirstName());
            customer.setLastName(order.getCustomer().getLastName());
            customer.setEmail(order.getCustomer().getEmail());
            customer.setPhone(order.getCustomer().getPhone());
            dto.setCustomer(customer);
        }

        // =========================
        // Address
        // =========================
        if (order.getShippingAddress() != null) {
            OrderResponseDTO.AddressDTO address = new OrderResponseDTO.AddressDTO();
            address.setId(order.getShippingAddress().getId());
            address.setStreetAddress(order.getShippingAddress().getStreetAddress());
            address.setPostalCode(order.getShippingAddress().getPostalCode());
            address.setCity(order.getShippingAddress().getCity());
            address.setCountry(order.getShippingAddress().getCountry());
            dto.setShippingAddress(address);
        }

        // =========================
        // Items
        // =========================

        List<OrderResponseDTO.ItemDTO> items = Collections.emptyList();
        if (order.getItems() != null) {
            items = order.getItems()
                    .stream()
                    .filter(Objects::nonNull)
                    .map(item -> {
                        OrderResponseDTO.ItemDTO itemDTO = new OrderResponseDTO.ItemDTO();
                        if (item.getProduct() != null) {
                            itemDTO.setProductId(item.getProduct().getId());
                            itemDTO.setProductName(item.getProduct().getName());
                        }
                        itemDTO.setQuantity(item.getQuantity());
                        itemDTO.setUnitPrice(item.getUnitPrice());
                        itemDTO.setLineTotal(item.getUnitPrice() == null || item.getQuantity() == null
                                ? BigDecimal.ZERO
                                : item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

                        return itemDTO;
                    })
                    .toList();
        }

        dto.setItems(items);

        // =========================
        // Total Price
        // =========================

        BigDecimal totalPrice = items.stream()
                .filter(Objects::nonNull)
                .map(item -> item == null ? null : item.getLineTotal())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, (left, right) -> left.add(right));

        dto.setTotalPrice(totalPrice);

        // =========================
        // Payments
        // =========================

        List<OrderResponseDTO.PaymentRequestDTO> payments = Collections.emptyList();
        if (order.getPayments() != null) {
            payments = order.getPayments()
                    .stream()
                    .filter(Objects::nonNull)
                    .map(payment -> {
                        OrderResponseDTO.PaymentRequestDTO paymentDTO = new OrderResponseDTO.PaymentRequestDTO();

                        if (payment.getPaymentType() != null) {
                            paymentDTO.setPaymentType(payment.getPaymentType());
                        }
                        if (payment.getAmount() != null) {
                            paymentDTO.setAmount(payment.getAmount());
                        }

                        return paymentDTO;
                    })
                    .toList();
        }

        dto.setPayments(payments);

        return dto;
    }
}