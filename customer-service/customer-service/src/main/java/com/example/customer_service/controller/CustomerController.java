package com.example.customer_service.controller;

import com.example.customer_service.dto.CustomerRequestDTO;
import com.example.customer_service.dto.CustomerResponseDTO;
import com.example.customer_service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping
  public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers(){
    return ResponseEntity.ok(customerService.getAllCustomers());
  }

  @GetMapping("/{customer_id}")
  public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long customer_id){
    return ResponseEntity.ok(customerService.getCustomerById(customer_id));
  }

  @PostMapping
  public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){
    return ResponseEntity.ok(customerService.createCustomer(customerRequestDTO));
  }

  @PutMapping("/{customer_id}")
  public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable Long customer_id, @RequestBody CustomerRequestDTO customerRequestDTO){
    return ResponseEntity.ok(customerService.updateCustomer(customer_id, customerRequestDTO));
  }

  @DeleteMapping("/{customer_id}")
  public ResponseEntity<String> deleteCustomer(@PathVariable Long customer_id){
    return ResponseEntity.ok(customerService.deleteCustomer(customer_id));
  }
}
