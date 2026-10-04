package com.thanh.project.view;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_summary_view")
@Immutable
public class PaymentSummaryView {

    @Id
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    public Long getPaymentId() { return paymentId; }
    public Long getOrderId() { return orderId; }
    public Double getAmount() { return amount; }
    public String getPaymentType() { return paymentType; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
}

