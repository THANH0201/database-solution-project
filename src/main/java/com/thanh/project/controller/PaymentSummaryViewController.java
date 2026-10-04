package com.thanh.project.controller;

import com.thanh.project.repository.PaymentSummaryViewRepo;
import com.thanh.project.view.PaymentSummaryView;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/views/payments/summary")
public class PaymentSummaryViewController {

    private final PaymentSummaryViewRepo repo;

    public PaymentSummaryViewController(PaymentSummaryViewRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<PaymentSummaryView> getAll() {
        return repo.findAll();
    }
}
