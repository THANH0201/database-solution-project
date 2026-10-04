package com.thanh.project.controller;

import com.thanh.project.entity.PaymentInheritance;
import com.thanh.project.entity.Order;
import com.thanh.project.dto.PaymentRequestDTO;
import com.thanh.project.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping
    public List<PaymentInheritance> getAllPayments() {
        return service.getAllPayments();
    }

    @GetMapping("/{id}")
    public PaymentInheritance getPaymentById(@PathVariable Long id) {
        return service.getPaymentById(id);
    }

    @PostMapping
    public PaymentInheritance createPayment(@RequestPart("order") Order order,
                                            @RequestPart("payment") PaymentRequestDTO paymentRequest) {
        return service.createPayment(order, paymentRequest);
    }

    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable Long id) {
        service.deletePayment(id);
        return "Payment deleted successfully";
    }
}

