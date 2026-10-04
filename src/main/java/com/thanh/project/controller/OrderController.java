package com.thanh.project.controller;

import com.thanh.project.dto.OrderDTO;
import com.thanh.project.dto.OrderResponseDTO;
import com.thanh.project.dto.UpdateDeliveryDateDTO;
import com.thanh.project.dto.UpdateStatusDTO;
import com.thanh.project.entity.Order;
import com.thanh.project.mapper.OrderMapper;
import com.thanh.project.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping
    public List<OrderResponseDTO> getAll() {
        return service.getAllOrders()
                .stream()
                .map(OrderMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getById(@PathVariable Integer id) {
        Order order = service.getOrder(id);
        return OrderMapper.toDTO(order);
    }

    @PostMapping
    public OrderResponseDTO create(@Valid @RequestBody OrderDTO dto) {
        Order order = service.createOrder(dto);
        return OrderMapper.toDTO(order);
    }

    @PutMapping("/{id}/status")
    public OrderResponseDTO updateStatus(@PathVariable Integer id, @Valid @RequestBody UpdateStatusDTO dto) {
        return OrderMapper.toDTO(service.updateStatus(id, dto.getStatus()));
    }

    @PutMapping("/{id}/delivery-date")
    public OrderResponseDTO updateDeliveryDate(@PathVariable Integer id, @Valid @RequestBody UpdateDeliveryDateDTO dto) {
        return OrderMapper.toDTO(service.updateDeliveryDate(id, dto.getDeliveryDate()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.deleteOrder(id); }
}
