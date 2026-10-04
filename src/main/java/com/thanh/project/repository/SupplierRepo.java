package com.thanh.project.repository;

import com.thanh.project.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SupplierRepo extends JpaRepository<Supplier, Integer> {}
