package com.thanh.project.controller;

import com.thanh.project.entity.Contact;
import com.thanh.project.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;

    @GetMapping
    public List<Contact> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public Contact getById(@PathVariable Integer id) { return service.getById(id); }

    @PostMapping
    public Contact create(@RequestBody Contact contact) { return service.create(contact); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}

