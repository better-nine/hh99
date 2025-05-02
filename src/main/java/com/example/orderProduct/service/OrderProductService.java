package com.example.orderProduct.service;

import com.example.orderProduct.entity.OrderProduct;
import com.example.orderProduct.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public OrderProduct createOrderProduct(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }


}
