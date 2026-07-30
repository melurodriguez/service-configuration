package com.example.customer_service.controller;

import com.example.customer_service.controller.CustomerController;
import com.example.customer_service.dto.CustomerRequestDTO;
import com.example.customer_service.dto.CustomerResponseDTO;
import com.example.customer_service.dto.ProductRequestDTO;
import com.example.customer_service.dto.ProductResponseDTO;
import com.example.customer_service.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private CustomerService customerService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldReturnAllCustomers() throws Exception {

    CustomerResponseDTO customer = new CustomerResponseDTO();
    customer.setId(1L);
    customer.setName("Melina");

    Mockito.when(customerService.getAllCustomers())
            .thenReturn(List.of(customer));

    mockMvc.perform(get("/customers"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("Melina"));
  }

  @Test
  void shouldReturnCustomerById() throws Exception {

    CustomerResponseDTO customer = new CustomerResponseDTO();
    customer.setId(1L);
    customer.setName("Melina");

    Mockito.when(customerService.getCustomerById(1L))
            .thenReturn(customer);

    mockMvc.perform(get("/customers/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Melina"));
  }

  @Test
  void shouldCreateCustomer() throws Exception {

    CustomerRequestDTO request = new CustomerRequestDTO();
    request.setName("Melina");

    CustomerResponseDTO response = new CustomerResponseDTO();
    response.setId(1L);
    response.setName("Melina");

    Mockito.when(customerService.createCustomer(any()))
            .thenReturn(response);

    mockMvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Melina"));
  }

  @Test
  void shouldUpdateCustomer() throws Exception {

    CustomerRequestDTO request = new CustomerRequestDTO();
    request.setName("Updated");

    CustomerResponseDTO response = new CustomerResponseDTO();
    response.setId(1L);
    response.setName("Updated");

    Mockito.when(customerService.updateCustomer(eq(1L), any()))
            .thenReturn(response);

    mockMvc.perform(put("/customers/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Updated"));
  }

  @Test
  void shouldDeleteCustomer() throws Exception {

    Mockito.when(customerService.deleteCustomer(1L))
            .thenReturn("Customer has been successfully deleted");

    mockMvc.perform(delete("/customers/1"))
            .andExpect(status().isOk())
            .andExpect(content().string("Customer has been successfully deleted"));
  }

  @Test
  void shouldCreateProductForCustomer() throws Exception {

    ProductRequestDTO request = new ProductRequestDTO();
    request.setType("Card");
    request.setBalance(BigDecimal.valueOf(1200));

    ProductResponseDTO response = new ProductResponseDTO();
    response.setId(10L);
    response.setType("Card");

    Mockito.when(customerService.createProductByCustomer(eq(1L), any()))
            .thenReturn(response);

    mockMvc.perform(post("/customers/1/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(10))
            .andExpect(jsonPath("$.type").value("Card"));
  }

  @Test
  void shouldReturnProductById() throws Exception {

    ProductResponseDTO response = new ProductResponseDTO();
    response.setId(10L);
    response.setType("Loan");

    Mockito.when(customerService.getProductById(1L, 10L))
            .thenReturn(response);

    mockMvc.perform(get("/customers/1/products/10"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(10))
            .andExpect(jsonPath("$.type").value("Loan"));
  }

  @Test
  void shouldUpdateProduct() throws Exception {

    ProductRequestDTO request = new ProductRequestDTO();
    request.setType("Card");

    ProductResponseDTO response = new ProductResponseDTO();
    response.setId(10L);
    response.setType("Loan");

    Mockito.when(customerService.updateProductByCustomer(eq(1L), eq(10L), any()))
            .thenReturn(response);

    mockMvc.perform(put("/customers/1/products/10")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.type").value("Loan"));
  }

  @Test
  void shouldDeleteProduct() throws Exception {

    Mockito.when(customerService.deleteProductByCustomer(1L, 10L))
            .thenReturn("Product has been successfully deleted");

    mockMvc.perform(delete("/customers/1/products/10"))
            .andExpect(status().isOk())
            .andExpect(content().string("Product has been successfully deleted"));
  }

}