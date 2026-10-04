package com.thanh.project.service;

import com.thanh.project.entity.Supplier;
import com.thanh.project.repository.SupplierRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepo repo;

    public List<Supplier> getAll() {
        return repo.findAll();
    }

    public Supplier getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    public Supplier create(Supplier supplier) {
        return repo.save(supplier);
    }

    public Supplier update(Integer id, Supplier updated) {
        Supplier s = getById(id);
        s.setName(updated.getName());
        s.setContactName(updated.getContactName());
        s.setPhone(updated.getPhone());
        s.setEmail(updated.getEmail());
        return repo.save(s);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

