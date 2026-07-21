package com.example.customer_service.client;

import com.example.customer_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

  @GetMapping("/products/customer/{customerId}")
  List<ProductDTO> getProductsByCustomerId(@PathVariable("customerId") Long customerId);
}
