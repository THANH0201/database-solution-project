package com.thanh.project.view;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import java.time.LocalDate;

@Entity
@Table(name = "daily_payment_view")
@Immutable
public class DailyPaymentView {

    @Id
    @Column(name = "payment_day")
    private LocalDate paymentDay;

    @Column(name = "total_payment")
    private Double totalPayment;

    public LocalDate getPaymentDay() { return paymentDay; }
    public Double getTotalPayment() { return totalPayment; }
}
