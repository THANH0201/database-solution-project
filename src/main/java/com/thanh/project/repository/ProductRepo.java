package com.thanh.project.repository;

import com.thanh.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepo extends JpaRepository<Product, Integer> {}