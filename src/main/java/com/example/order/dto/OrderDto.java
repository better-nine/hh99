package com.example.order.dto;

import com.example.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class OrderDto {
    private String storeId;
    private String productId;
    private String stockId;
    private Long quantity;
    private OrderStatus status;
    private Timestamp orderDateTime;
}
