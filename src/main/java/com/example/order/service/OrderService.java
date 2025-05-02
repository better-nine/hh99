package com.example.order.service;

import com.example.order.dto.OrderDto;
import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import com.example.orderProduct.service.OrderProductService;
import com.example.stock.service.StockService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final StockService stockService;
    private final OrderProductService orderProductService;

    /**
     * 주문 등록
     * @param dto
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public Order createOrder(OrderDto dto) {
        String orderId = UUID.randomUUID().toString();
        Order order = Order.builder()
                .orderId(orderId)
                .status(dto.getStatus())
                .orderDateTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        orderRepository.save(order);

        // 재고 감소 및 주문 상품 등록
        dto.getOrderProduct().forEach(orderProductDto -> {
            stockService.decreaseStock(orderProductDto.getProductId(), orderProductDto.getQuantity());
            orderProductService.createOrderProduct(orderId, orderProductDto);
        });

        return order;
    }

}
