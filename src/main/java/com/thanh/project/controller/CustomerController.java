package com.thanh.project.controller;

import com.thanh.project.dto.CustomerDTO;
import com.thanh.project.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public List<CustomerDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CustomerDTO getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public CustomerDTO create(
            @Valid @RequestBody CustomerDTO dto) {

        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CustomerDTO update(
            @PathVariable Integer id,
            @Valid @RequestBody CustomerDTO dto) {

        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}