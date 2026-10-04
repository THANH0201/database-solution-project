package com.thanh.project.controller;

import com.thanh.project.entity.CustomerAddress;
import com.thanh.project.service.CustomerAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer-addresses")
@RequiredArgsConstructor
public class CustomerAddressController {

    private final CustomerAddressService service;

    @GetMapping
    public List<CustomerAddress> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public CustomerAddress getById(@PathVariable Integer id) { return service.getById(id); }

    @PostMapping
    public CustomerAddress create(@RequestBody CustomerAddress address) {
        return service.create(address);
    }

    @PutMapping("/{id}")
    public CustomerAddress update(@PathVariable Integer id, @RequestBody CustomerAddress address) {
        return service.update(id, address);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}

