package com.thanh.project.repository;

import com.thanh.project.view.RevenuePaymentCompareView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface RevenuePaymentCompareViewRepo extends JpaRepository<RevenuePaymentCompareView, LocalDate> {
    Optional<RevenuePaymentCompareView> findByRevenueDate(LocalDate revenueDate);
}

