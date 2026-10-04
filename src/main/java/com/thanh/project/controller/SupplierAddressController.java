package com.thanh.project.controller;

import com.thanh.project.entity.SupplierAddress;
import com.thanh.project.service.SupplierAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier-addresses")
@RequiredArgsConstructor
public class SupplierAddressController {

    private final SupplierAddressService service;

    @GetMapping
    public List<SupplierAddress> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public SupplierAddress getById(@PathVariable Integer id) { return service.getById(id); }

    @PostMapping
    public SupplierAddress create(@RequestBody SupplierAddress address) {
        return service.create(address);
    }

    @PutMapping("/{id}")
    public SupplierAddress update(@PathVariable Integer id, @RequestBody SupplierAddress address) {
        return service.update(id, address);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}

