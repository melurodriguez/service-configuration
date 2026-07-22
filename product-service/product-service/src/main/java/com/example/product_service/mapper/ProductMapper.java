package com.example.product_service.mapper;

import com.example.product_service.dto.ProductRequestDTO;
import com.example.product_service.dto.ProductResponseDTO;
import com.example.product_service.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public Product toEntity(ProductRequestDTO requestDTO) {
    if (requestDTO == null) return null;

    Product product = new Product();
    product.setCustomerId(requestDTO.getCustomerId());
    product.setType(requestDTO.getType());
    product.setAccountNumber(requestDTO.getAccountNumber());
    product.setBalance(requestDTO.getBalance());

    return product;
  }

  public ProductResponseDTO toResponse(Product product) {
    if (product == null) return null;

    ProductResponseDTO responseDTO = new ProductResponseDTO();
    responseDTO.setId(product.getId());
    responseDTO.setCustomerId(product.getCustomerId());
    responseDTO.setType(product.getType());
    responseDTO.setAccountNumber(product.getAccountNumber());
    responseDTO.setBalance(product.getBalance());

    return responseDTO;
  }
}