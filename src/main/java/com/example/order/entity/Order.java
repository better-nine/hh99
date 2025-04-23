package com.example.order.entity;

import jakarta.persistence.*;
        import lombok.*;

        import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String orderId;
    private String storeId;
    private String productId;
    private String stockId;
    private Long quantity;
    private OrderStatus status;
    private Timestamp orderDateTime;
}
