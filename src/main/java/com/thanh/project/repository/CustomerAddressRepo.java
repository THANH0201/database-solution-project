package com.thanh.project.repository;

import com.thanh.project.entity.Customer;
import com.thanh.project.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface CustomerAddressRepo extends JpaRepository<CustomerAddress, Integer> {
    List<CustomerAddress> findByCustomer(Customer customer);
} 