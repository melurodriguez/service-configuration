package com.example.product_service.service;

import com.example.product_service.dto.ProductRequestDTO;
import com.example.product_service.dto.ProductResponseDTO;
import com.example.product_service.exception.ProductNotFoundException;
import com.example.product_service.mapper.ProductMapper;
import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
    Product product = productMapper.toEntity(requestDTO);
    Product savedProduct = productRepository.save(product);
    return productMapper.toResponse(savedProduct);
  }

  public List<ProductResponseDTO> getAllProducts() {
    return productRepository.findAll()
            .stream()
            .map(productMapper::toResponse)
            .toList();
  }

  public List<ProductResponseDTO> getProductsByCustomerId(Long customerId) {
    return productRepository.findByCustomerId(customerId)
            .stream()
            .map(productMapper::toResponse)
            .toList();
  }

  public ProductResponseDTO getProductById(Long id) {
    Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    return productMapper.toResponse(product);
  }
}