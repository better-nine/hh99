package com.example.order.service;

import com.example.order.dto.OrderDto;
import com.example.order.entity.Order;
import com.example.order.entity.OrderStatus;
import com.example.order.repository.OrderRepository;
import com.example.stock.service.StockService;
import com.fasterxml.jackson.databind.JsonMappingException;
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

    /**
     * 주문 등록
     * @param dto
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public Order createOrder(OrderDto dto) {
        Order order = Order.builder()
                .orderId(UUID.randomUUID().toString())
                .storeId(dto.getStoreId())
                .productId(dto.getProductId())
                .stockId(dto.getStockId())
                .quantity(dto.getQuantity())
                .build();

        // 재고 감소
        stockService.decreaseStock(dto.getStockId(), dto.getQuantity());
        return orderRepository.save(order);
    }

    /**
     * 주문 조회
     * @param orderId
     * @return
     */
    public Order getOrder(String orderId) {
        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    /**
     * 주문 수정
     * @param orderId
     * @param dto
     * @return
     * @throws JsonMappingException
     */
    @Transactional
    public Order updateOrder(String orderId, OrderDto dto) throws JsonMappingException {
        Order order = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        OrderStatus currentStatus = order.getStatus();
        OrderStatus newStatus = dto.getStatus();

        if (!currentStatus.canTransitionTo(newStatus)) {
            throw new IllegalStateException("Can't transition to " + newStatus + " to " + currentStatus);
        }

        order.setStatus(newStatus);
        order.setOrderDateTime(Timestamp.valueOf(LocalDateTime.now()));

        return orderRepository.save(order);
    }

    /**
     * 주문 삭제
     * @param orderId
     */
    public void deleteOrder(String orderId) {
        Order order = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        orderRepository.delete(order);
    }
}
