package com.yahir.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    private String description;

    @NotNull(message = "El precio es requerido")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double price;

    @NotNull(message = "El stock es requerido")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    public ProductDTO() {}

    public ProductDTO(String name, String description, Double price, Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}