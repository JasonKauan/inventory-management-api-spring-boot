package com.projeto.pizzaria.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProductDTO {

    @NotBlank(message = "product name is required")
    private String name;

    private String description;

    @NotNull(message = "price is required")
    @Positive(message = "price must be greater than zero")
    private BigDecimal price;

    @NotBlank(message = "category is required")
    private String category;

    // getters e setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}