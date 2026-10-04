package com.thanh.project.controller;

import com.thanh.project.repository.DailyPaymentViewRepo;
import com.thanh.project.view.DailyPaymentView;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/views/payments/daily")
public class DailyPaymentViewController {

    private final DailyPaymentViewRepo repo;

    public DailyPaymentViewController(DailyPaymentViewRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<DailyPaymentView> getAll() {
        return repo.findAll();
    }
}

