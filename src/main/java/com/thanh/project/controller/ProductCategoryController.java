package com.thanh.project.controller;

import com.thanh.project.entity.ProductCategory;
import com.thanh.project.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService service;

    @GetMapping
    public List<ProductCategory> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public ProductCategory getById(@PathVariable Integer id) { return service.getById(id); }

    @PostMapping
    public ProductCategory create(@RequestBody ProductCategory category) {
        return service.create(category);
    }

    @PutMapping("/{id}")
    public ProductCategory update(@PathVariable Integer id, @RequestBody ProductCategory category) {
        return service.update(id, category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}

