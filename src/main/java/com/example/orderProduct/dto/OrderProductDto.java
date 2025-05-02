package com.example.orderProduct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderProductDto {
    private String orderProductId;
    private String orderId;
    private String productId;
    private long quantity;
}
