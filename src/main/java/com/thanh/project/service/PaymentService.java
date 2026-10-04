package com.thanh.project.service;

import com.thanh.project.entity.Order;
import com.thanh.project.entity.PaymentInheritance;
import com.thanh.project.entity.Card;
import com.thanh.project.entity.Voucher;
import com.thanh.project.repository.PaymentInheritanceRepo;
import com.thanh.project.dto.PaymentRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentInheritanceRepo paymentRepo;

    // ============================
    // 1. GET ALL PAYMENTS
    // ============================
    public List<PaymentInheritance> getAllPayments() {
        return paymentRepo.findAll();
    }

    // ============================
    // 2. GET PAYMENT BY ID
    // ============================
    public PaymentInheritance getPaymentById(Long id) {
        return paymentRepo.findById(id).orElse(null);
    }

    // ============================
    // 3. CREATE PAYMENT (Inheritance)
    // ============================
    @Transactional
    public PaymentInheritance createPayment(
            Order order, PaymentRequestDTO dto) {

        PaymentInheritance payment;

        switch (dto.getPaymentType().toLowerCase()) {

            case "card":

                Card card = new Card();

                card.setCardNumber(
                        dto.getCardNumber());

                card.setCardHolder(
                        dto.getCardHolder());

                card.setExpiryDate(
                        dto.getExpiryDate());

                payment = card;

                break;

            case "voucher":

                Voucher voucher = new Voucher();

                voucher.setVoucherCode(
                        dto.getVoucherCode());

                voucher.setDiscountAmount(
                        dto.getDiscountAmount());

                payment = voucher;

                break;

            default:

                throw new IllegalArgumentException(
                        "Unsupported payment type");
        }

        payment.setAmount(
                dto.getAmount());

        payment.setPaymentType(
                dto.getPaymentType());

        payment.setPaymentDate(
                LocalDateTime.now());

        payment.setOrder(order);

        return payment;
    }

    // ============================
    // 4. DELETE PAYMENT
    // ============================
    @Transactional
    public void deletePayment(Long id) {
        paymentRepo.deleteById(id);
    }
}
