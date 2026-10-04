package com.thanh.project.repository;

import com.thanh.project.view.PaymentSummaryView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentSummaryViewRepo extends JpaRepository<PaymentSummaryView, Long> {
}
