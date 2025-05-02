package com.example.order.dto;

import com.example.order.entity.OrderStatus;
import com.example.orderProduct.dto.OrderProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDto {
    private String orderId;
    private OrderStatus status;
    private Timestamp orderDateTime;

    private List<OrderProductDto> orderProductDtoList;
}
