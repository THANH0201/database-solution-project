package com.thanh.project.service;

import com.thanh.project.entity.Contact;
import com.thanh.project.exception.ResourceNotFoundException;
import com.thanh.project.repository.ContactRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepo repo;

    public List<Contact> getAll() {
        return repo.findAll();
    }

    public Contact getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
    }

    public Contact create(Contact contact) {
        return repo.save(contact);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

