package com.example.product.controller;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.service.ProductService;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody ProductDto dto) {
        return productService.createProduct(dto);
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable String productId,
                                 @RequestBody ProductDto dto) throws JsonMappingException {
        return productService.updateProduct(productId, dto);
    }

    @DeleteMapping("/{productId}")
    public boolean deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return true;
    }
}
