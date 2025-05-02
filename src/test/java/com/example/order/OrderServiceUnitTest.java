package com.example.order;

import com.example.order.dto.OrderDto;
import com.example.order.entity.Order;
import com.example.order.entity.OrderStatus;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import com.example.orderProduct.dto.OrderProductDto;
import com.example.orderProduct.entity.OrderProduct;
import com.example.orderProduct.service.OrderProductService;
import com.example.stock.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceUnitTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private StockService stockService;

    @Mock
    private OrderProductService orderProductService;

    @InjectMocks
    private OrderService orderService;

    private Order order;
    private OrderDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        order = new Order();
        order.setOrderId("orderId");
        order.setOrderDateTime(timestamp);
        order.setStatus(OrderStatus.CREATED);

        List<OrderProductDto> orderProductDtoList = new ArrayList<>();
        orderProductDtoList.add(new OrderProductDto("orderProductId1", "orderId1", "productId1", 5));
        orderProductDtoList.add(new OrderProductDto("orderProductId2", "orderId1", "productId2", 3));

        dto = new OrderDto("orderId", OrderStatus.CREATED, timestamp, orderProductDtoList);
    }

    @Test
    void createOrder_단위테스트() {
        // When
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        Order createdOrder = orderService.createOrder(dto);

        // Then
        assertNotNull(createdOrder);
        assertEquals(order.getOrderId(), createdOrder.getOrderId());

        verify(orderRepository, times(1)).save(any(Order.class));
        verify(stockService, times(2)).decreaseStock(anyString(), anyLong());
        verify(orderProductService, times(2)).createOrderProduct(any(OrderProduct.class));

    }

}
