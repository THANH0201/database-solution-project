package com.thanh.project.service;

import com.thanh.project.entity.ProductCategory;
import com.thanh.project.repository.ProductCategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepo repo;

    public List<ProductCategory> getAll() {
        return repo.findAll();
    }

    public ProductCategory getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public ProductCategory create(ProductCategory category) {
        return repo.save(category);
    }

    public ProductCategory update(Integer id, ProductCategory updated) {
        ProductCategory c = getById(id);
        c.setName(updated.getName());
        c.setDescription(updated.getDescription());
        return repo.save(c);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

