package com.thanh.project.repository;

import com.thanh.project.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {} 

