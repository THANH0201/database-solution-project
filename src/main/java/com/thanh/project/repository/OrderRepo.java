package com.thanh.project.repository;

import com.thanh.project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {} 
