package com.thanh.project.controller;


import com.thanh.project.entity.Supplier;
import com.thanh.project.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService service;

    @GetMapping
    public List<Supplier> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public Supplier getById(@PathVariable Integer id) { return service.getById(id); }

    @PostMapping
    public Supplier create(@RequestBody Supplier supplier) { return service.create(supplier); }

    @PutMapping("/{id}")
    public Supplier update(@PathVariable Integer id, @RequestBody Supplier supplier) {
        return service.update(id, supplier);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}

