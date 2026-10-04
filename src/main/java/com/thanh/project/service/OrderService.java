package com.thanh.project.service;

import com.thanh.project.dto.OrderDTO;
import com.thanh.project.dto.PaymentRequestDTO;
import com.thanh.project.entity.*;
import com.thanh.project.exception.ResourceNotFoundException;
import com.thanh.project.repository.*;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

        private final CustomerRepo customerRepo;
        private final CustomerAddressRepo addressRepo;
        private final ProductRepo productRepo;
        private final OrderRepo orderRepo;
        private final PaymentService paymentService;

        // Create order
        @Transactional
        public Order createOrder(OrderDTO dto) {

                Customer customer = customerRepo.findById(dto.getCustomerId())
                                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

                CustomerAddress address = addressRepo.findById(
                                dto.getShippingAddressId())
                                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

                Order order = new Order();

                order.setCustomer(customer);
                order.setShippingAddress(address);
                order.setOrderDate(LocalDateTime.now());
                order.setDeliveryDate(dto.getDeliveryDate());
                order.setStatus(dto.getStatus());

                // =========================
                // Create Order Items
                // =========================

                BigDecimal orderTotal = BigDecimal.ZERO;

                for (OrderDTO.OrderItemDTO itemDTO : dto.getItems()) {

                        Product product = productRepo.findById(
                                        itemDTO.getProductId())
                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                        "Product not found"));

                        if (product.getStockQuantity() < itemDTO.getQuantity()) {

                                throw new IllegalArgumentException(
                                                "Insufficient stock for product: "
                                                                + product.getName());
                        }

                        // Update stock
                        product.setStockQuantity(
                                        product.getStockQuantity()
                                                        - itemDTO.getQuantity());

                        productRepo.save(product);

                        // Create order item
                        OrderItem item = new OrderItem();

                        item.setOrder(order);
                        item.setProduct(product);
                        item.setQuantity(itemDTO.getQuantity());
                        item.setUnitPrice(product.getPrice());

                        order.getItems().add(item);

                        // Calculate order total
                        orderTotal = orderTotal.add(
                                        product.getPrice().multiply(
                                                        BigDecimal.valueOf(
                                                                        itemDTO.getQuantity())));
                }

                // =========================
                // Create Payments
                // =========================

                double paymentTotal = 0.0;

                if (dto.getPayments() != null &&
                                !dto.getPayments().isEmpty()) {

                        for (PaymentRequestDTO paymentDTO : dto.getPayments()) {

                                PaymentInheritance payment = paymentService.createPayment(
                                                order,
                                                paymentDTO);

                                order.getPayments().add(payment);

                                paymentTotal += payment.getAmount();
                        }

                        // Validate payment total
                        if (Double.compare(
                                        paymentTotal,
                                        orderTotal.doubleValue()) != 0) {

                                throw new IllegalArgumentException(
                                                "Payment total ("
                                                                + paymentTotal
                                                                + ") must equal order total ("
                                                                + orderTotal
                                                                + ")");
                        }
                } else {

                        throw new IllegalArgumentException(
                                        "At least one payment is required");
                }

                return orderRepo.save(order);
        }

        // get all
        public List<Order> getAllOrders() {
                return orderRepo.findAll();
        }

        // get order by ID
        public Order getOrder(Integer id) {

                return orderRepo.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Order not found"));
        }

        // Update status
        @Transactional
        public Order updateStatus(
                        Integer id,
                        String status) {

                Order order = getOrder(id);

                order.setStatus(status);

                return orderRepo.save(order);
        }

        // Update Delivery date
        @Transactional
        public Order updateDeliveryDate(
                        Integer id,
                        LocalDateTime deliveryDate) {

                Order order = getOrder(id);

                order.setDeliveryDate(deliveryDate);

                return orderRepo.save(order);
        }

        // Delete order
        @Transactional
        public void deleteOrder(Integer id) {
                Order order = getOrder(id);

                for (OrderItem item : order.getItems()) {

                        Product product = item.getProduct();

                        product.setStockQuantity(
                                        product.getStockQuantity() + item.getQuantity());

                        productRepo.save(product);
                }

                orderRepo.delete(order);
        }

}