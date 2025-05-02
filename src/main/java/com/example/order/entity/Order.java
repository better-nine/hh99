package com.example.order.entity;

import jakarta.persistence.*;
        import lombok.*;

        import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "order")
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
    private OrderStatus status;
    private Timestamp orderDateTime;
}
