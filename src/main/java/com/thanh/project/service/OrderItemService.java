package com.thanh.project.service;

import com.thanh.project.entity.OrderItem;
import com.thanh.project.repository.OrderItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepo repo;

    public List<OrderItem> getAll() {
        return repo.findAll();
    }

    public OrderItem getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order item not found"));
    }

    public OrderItem create(OrderItem item) {
        return repo.save(item);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

