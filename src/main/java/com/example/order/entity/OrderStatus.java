package com.example.order.entity;

public enum OrderStatus {
    CREATED,
    PAYMENT_PENDING,
    PAYMENT_COMPLETED,
    SHIPPED,
    CANCELLED;

    public boolean canTransitionTo(OrderStatus newStatus) {
        return switch (this) {
            case CREATED -> newStatus == PAYMENT_PENDING;
            case PAYMENT_PENDING -> newStatus == PAYMENT_COMPLETED || newStatus == CANCELLED;
            case PAYMENT_COMPLETED -> newStatus == SHIPPED || newStatus == CANCELLED;
            case SHIPPED, CANCELLED -> false;
        };
    }
}
