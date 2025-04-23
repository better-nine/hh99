package com.example.product.service;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 상품 등록
     * @param dto
     * @return Product
     */
    @Transactional
    public Product createProduct(ProductDto dto) {
        Product product = Product.builder()
                .productId(java.util.UUID.randomUUID().toString())
                .name(dto.getName())
                .price(dto.getPrice())
                .build();
        return productRepository.save(product);
    }

    /**
     * 상품 조회
     * @param productId
     * @return Product
     */
    public Product getProduct(String productId) {
        return productRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    /**
     * 상품 수정
     * @param productId
     * @param dto
     * @return Product
     * @throws JsonMappingException
     */
    @Transactional
    public Product updateProduct(String productId, ProductDto dto) throws JsonMappingException {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        return productRepository.save(product);
    }

    /**
     * 상품 삭제
     * @param productId
     */
    public void deleteProduct(String productId) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        productRepository.delete(product);
    }

}
