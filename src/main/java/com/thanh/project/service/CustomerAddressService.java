package com.thanh.project.service;

import com.thanh.project.entity.Customer;
import com.thanh.project.entity.CustomerAddress;
import com.thanh.project.exception.ResourceNotFoundException;
import com.thanh.project.repository.CustomerAddressRepo;
import com.thanh.project.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerAddressService {

    private final CustomerAddressRepo repo;
    private final CustomerRepo customerRepo;

    public List<CustomerAddress> getAll() {
        return repo.findAll();
    }

    public CustomerAddress getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer address not found"));
    }

    public CustomerAddress create(CustomerAddress address) {
        Integer customerId = address.getCustomer().getId();
        Customer customer = customerRepo.findById(customerId).orElseThrow(() ->new ResourceNotFoundException("Customer not found"));
        address.setCustomer(customer);
        return repo.save(address);
    }

    public CustomerAddress update(Integer id, CustomerAddress updated) {
        CustomerAddress a = getById(id);
        a.setStreetAddress(updated.getStreetAddress());
        a.setPostalCode(updated.getPostalCode());
        a.setCity(updated.getCity());
        a.setCountry(updated.getCountry());
        a.setCustomer(updated.getCustomer());
        return repo.save(a);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

