package com.thanh.project.service;

import com.thanh.project.dto.CustomerDTO;
import com.thanh.project.entity.Customer;
import com.thanh.project.entity.CustomerAddress;
import com.thanh.project.repository.CustomerAddressRepo;
import com.thanh.project.repository.CustomerRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerAddressRepo addressRepo;

    public List<CustomerDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public CustomerDTO getById(Integer id) {

        Customer customer = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return convertToDTO(customer);
    }

    public CustomerDTO create(CustomerDTO dto) {

        Customer customer = new Customer();

        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());

        Customer savedCustomer = repo.save(customer);

        CustomerAddress address = new CustomerAddress();

        address.setStreetAddress(dto.getStreetAddress());
        address.setPostalCode(dto.getPostalCode());
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setCustomer(savedCustomer);

        addressRepo.save(address);

        return convertToDTO(savedCustomer);
    }

    public CustomerDTO update(Integer id, CustomerDTO dto) {

        Customer customer = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());

        Customer savedCustomer = repo.save(customer);

        List<CustomerAddress> addresses =
                addressRepo.findByCustomer(savedCustomer);

        if (!addresses.isEmpty()) {

            CustomerAddress address = addresses.get(0);

            address.setStreetAddress(dto.getStreetAddress());
            address.setPostalCode(dto.getPostalCode());
            address.setCity(dto.getCity());
            address.setCountry(dto.getCountry());

            addressRepo.save(address);
        }

        return convertToDTO(savedCustomer);
    }

    public void delete(Integer id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("Customer not found");
        }

        repo.deleteById(id);
    }

    private CustomerDTO convertToDTO(Customer customer) {

        CustomerDTO dto = new CustomerDTO();

        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());

        if (customer.getAddresses() != null &&
                !customer.getAddresses().isEmpty()) {

            CustomerAddress address =
                    customer.getAddresses().get(0);

            dto.setStreetAddress(address.getStreetAddress());
            dto.setPostalCode(address.getPostalCode());
            dto.setCity(address.getCity());
            dto.setCountry(address.getCountry());
        }

        return dto;
    }
}