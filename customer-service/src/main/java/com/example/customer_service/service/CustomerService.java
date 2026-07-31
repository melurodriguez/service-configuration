package com.example.customer_service.service;

import com.example.customer_service.client.ProductClient;
import com.example.customer_service.dto.CustomerRequestDTO;
import com.example.customer_service.dto.CustomerResponseDTO;
import com.example.customer_service.dto.ProductRequestDTO;
import com.example.customer_service.dto.ProductResponseDTO;
import com.example.customer_service.exception.CustomerNotFoundException;
import com.example.customer_service.exception.ProductNotFoundException;
import com.example.customer_service.exception.ProductOwnershipException;
import com.example.customer_service.mapper.CustomerMapper;
import com.example.customer_service.model.Customer;
import com.example.customer_service.repository.CustomerRepository;
import feign.FeignException;
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

    List<ProductResponseDTO> products= productClient.getProductsByCustomerId(customer.getId());
    CustomerResponseDTO response = customerMapper.toResponse(customer);
    response.setProducts(products);

    return response;
  }

  public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
    Customer customer= customerMapper.toEntity(customerRequestDTO);
    Customer savedCustomer= customerRepository.save(customer);
    return customerMapper.toResponse(savedCustomer);
  }

  public CustomerResponseDTO updateCustomer(
          Long customerId,
          CustomerRequestDTO customerRequestDTO) {

    Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() ->
                    new CustomerNotFoundException(
                            "Customer not found with ID: " + customerId));

    customer.setName(customerRequestDTO.getName());
    customer.setDocument(customerRequestDTO.getDocument());
    customer.setEmail(customerRequestDTO.getEmail());
    customer.setBalance(customerRequestDTO.getBalance());

    customerRepository.save(customer);

    return getCustomerById(customerId);
  }

  public String deleteCustomer(Long customerId) {
    if (!customerRepository.existsById(customerId)) {
      throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
    }

    customerRepository.deleteById(customerId);
    return "Customer has been successfully deleted";
  }

  public List<ProductResponseDTO> getProductsByCustomer(Long customerId){
    getCustomerById(customerId);
    return productClient.getProductsByCustomerId(customerId);
  }

  public ProductResponseDTO createProductByCustomer(Long customerId, ProductRequestDTO requestDTO) {
    getCustomerById(customerId);
    requestDTO.setCustomerId(customerId);
    return productClient.createProduct(requestDTO);
  }

  public ProductResponseDTO getProductById(Long customerId, Long productId) {
    getCustomerById(customerId);

    ProductResponseDTO product;

    try {
      product = productClient.getProductById(productId);
    } catch (FeignException.NotFound ex) {
      throw new ProductNotFoundException(
              "Product not found with ID: " + productId);
    }

    if (!product.getCustomerId().equals(customerId)) {
      throw new ProductOwnershipException(
              "The product does not belong to the specified customer.");
    }

    return product;
  }

  public ProductResponseDTO updateProductByCustomer(
          Long customerId,
          Long productId,
          ProductRequestDTO requestDTO) {

    getProductById(customerId, productId);
    requestDTO.setCustomerId(customerId);
    return productClient.updateProduct(productId, requestDTO);
  }

  public String deleteProductByCustomer(Long customerId, Long productId) {
    getProductById(customerId, productId);
    productClient.deleteProduct(productId);
    return "Product has been successfully deleted";
  }
}
