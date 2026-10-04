package com.thanh.project.repository;

import com.thanh.project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepo extends JpaRepository<Customer, Integer> {} 
