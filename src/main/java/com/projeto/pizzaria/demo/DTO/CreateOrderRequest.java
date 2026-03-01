package com.projeto.pizzaria.demo.DTO;

import java.util.List;
import java.util.UUID;

public class CreateOrderRequest {


    private UUID userId;


    private List<OrderItemRequest> items;

    // Getters and Setters


    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

    public UUID getUserId() {
        return userId;
    }
}
