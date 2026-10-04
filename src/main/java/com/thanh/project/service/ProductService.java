package com.thanh.project.service;

import com.thanh.project.dto.ProductDTO;
import com.thanh.project.entity.Product;
import com.thanh.project.entity.ProductCategory;
import com.thanh.project.entity.Supplier;
import com.thanh.project.repository.ProductCategoryRepo;
import com.thanh.project.repository.ProductRepo;
import com.thanh.project.repository.SupplierRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo repo;
    private final ProductCategoryRepo categoryRepo;
    private final SupplierRepo supplierRepo;

    public List<ProductDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public ProductDTO getById(Integer id) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return convertToDTO(product);
    }

    public ProductDTO create(ProductDTO dto) {

        ProductCategory category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(BigDecimal.valueOf(dto.getPrice()));
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(category);
        product.setSupplier(supplier);

        return convertToDTO(repo.save(product));
    }

    public ProductDTO update(Integer id, ProductDTO dto) {

        Product product = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductCategory category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(BigDecimal.valueOf(dto.getPrice()));
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(category);
        product.setSupplier(supplier);

        return convertToDTO(repo.save(product));
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice() != null ? product.getPrice().doubleValue() : 0.0);
        dto.setStockQuantity(product.getStockQuantity());
        dto.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
        dto.setSupplierId(product.getSupplier() != null ? product.getSupplier().getId() : null);
        dto.setSupplierName(product.getSupplier() != null ? product.getSupplier().getName() : null);
        return dto;
    }

    public void delete(Integer id) {

        if (!repo.existsById(id)) {
            repo.deleteById(id);  
        }
    }
}

