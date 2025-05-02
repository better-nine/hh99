package com.example.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    private String productId;
    private String productName;
    private Long productPrice;
    private String description;
    private String storeId;
}
