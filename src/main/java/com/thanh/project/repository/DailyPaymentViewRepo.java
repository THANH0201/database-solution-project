package com.thanh.project.repository;

import com.thanh.project.view.DailyPaymentView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyPaymentViewRepo extends JpaRepository<DailyPaymentView, java.time.LocalDate> {
}
