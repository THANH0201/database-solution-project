package com.thanh.project.controller;

import com.thanh.project.repository.RevenuePaymentCompareViewRepo;
import com.thanh.project.view.RevenuePaymentCompareView;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/views/revenue/compare")
public class RevenuePaymentCompareViewController {

    private final RevenuePaymentCompareViewRepo repo;
    
    public RevenuePaymentCompareViewController(RevenuePaymentCompareViewRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/{date}")
    public RevenuePaymentCompareView getByDate(@PathVariable LocalDate date) {
        return repo.findByRevenueDate(date).orElseThrow(() ->new RuntimeException("No revenue data found for date: " + date));
    }
}

