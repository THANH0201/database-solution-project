package com.thanh.project.service;

import com.thanh.project.entity.SupplierAddress;
import com.thanh.project.exception.ResourceNotFoundException;
import com.thanh.project.repository.SupplierAddressRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierAddressService {

    private final SupplierAddressRepo repo;

    public List<SupplierAddress> getAll() {
        return repo.findAll();
    }

    public SupplierAddress getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier address not found"));
    }

    public SupplierAddress create(SupplierAddress address) {
        return repo.save(address);
    }

    public SupplierAddress update(Integer id, SupplierAddress updated) {
        SupplierAddress a = getById(id);
        a.setStreetAddress(updated.getStreetAddress());
        a.setPostalCode(updated.getPostalCode());
        a.setCity(updated.getCity());
        a.setCountry(updated.getCountry());
        a.setSupplier(updated.getSupplier());
        return repo.save(a);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

