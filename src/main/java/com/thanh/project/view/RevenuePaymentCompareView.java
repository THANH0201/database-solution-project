package com.thanh.project.view;

import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.Immutable;
import java.time.LocalDate;

@Entity
@Table(name = "revenue_payment_compare_view")
@Immutable
@Data 
public class RevenuePaymentCompareView {

    @Id
    @Column(name = "revenue_date")
    private LocalDate revenueDate;

    @Column(name = "total_revenue")
    private Double totalRevenue;

    @Column(name = "total_payment")
    private Double totalPayment;

    @Column(name = "difference")
    private Double difference;

    public LocalDate getRevenueDate() { return revenueDate; }
    public Double getTotalRevenue() { return totalRevenue; }
    public Double getTotalPayment() { return totalPayment; }
    public Double getDifference() { return difference; }
}

