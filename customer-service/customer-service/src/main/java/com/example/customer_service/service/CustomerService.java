package com.example.customer_service.service;

import com.example.customer_service.client.ProductClient;
import com.example.customer_service.dto.CustomerRequestDTO;
import com.example.customer_service.dto.CustomerResponseDTO;
import com.example.customer_service.dto.ProductDTO;
import com.example.customer_service.exception.CustomerNotFoundException;
import com.example.customer_service.mapper.CustomerMapper;
import com.example.customer_service.model.Customer;
import com.example.customer_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final ProductClient productClient;
  private final CustomerMapper customerMapper;


  public List<CustomerResponseDTO> getAllCustomers() {
    return customerRepository.findAll()
            .stream()
            .map(customerMapper::toResponse)
            .toList();
  }

  public CustomerResponseDTO getCustomerById(Long customerId) {
    Customer customer= customerRepository.findById(customerId)
            .orElseThrow(()-> new CustomerNotFoundException("Customer not found with ID: " + customerId));

    List<ProductDTO> products= productClient.getProductsByCustomerId(customer.getId());
    return customerMapper.toResponse(customer);
  }

  public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
    Customer customer= customerMapper.toEntity(customerRequestDTO);
    Customer savedCustomer= customerRepository.save(customer);
    return customerMapper.toResponse(savedCustomer);
  }

  public CustomerResponseDTO updateCustomer(Long customerId, CustomerRequestDTO customerRequestDTO) {
    Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));

    customer.setName(customerRequestDTO.getName());
    customer.setDocument(customerRequestDTO.getDocument());
    customer.setEmail(customerRequestDTO.getEmail());
    customer.setBalance(customerRequestDTO.getBalance());

    Customer updatedCustomer = customerRepository.save(customer);
    return customerMapper.toResponse(updatedCustomer);
  }

  public String deleteCustomer(Long customerId) {
    if (!customerRepository.existsById(customerId)) {
      throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
    }

    customerRepository.deleteById(customerId);
    return "Customer has been successfully deleted";
  }
}
