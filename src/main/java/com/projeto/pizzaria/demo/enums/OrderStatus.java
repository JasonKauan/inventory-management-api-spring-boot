package com.projeto.pizzaria.demo.enums;

public enum OrderStatus {
    CREATED,
    PENDING,
    ACTIVE,
    IN_PROGRESS,
    COMPLETED,
    CANCELED;

    public boolean canChangeTo(OrderStatus newStatus) {
        return switch (this) {
            case CREATED -> newStatus == PENDING || newStatus == CANCELED;
            case PENDING -> newStatus == ACTIVE || newStatus == CANCELED;
            case ACTIVE -> newStatus == IN_PROGRESS || newStatus == CANCELED;
            case IN_PROGRESS -> newStatus == COMPLETED || newStatus == CANCELED;
            case COMPLETED, CANCELED -> false;
        };
    }
}
