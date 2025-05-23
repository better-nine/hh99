package com.example.stock.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String stockId;
    private String productId;
    private long stock;

    public boolean decrease(long quantity) {
        return stock >= quantity && (stock -= quantity) >= 0;
    }
}
