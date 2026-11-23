package com.example.order.dto;
import lombok.Data;

@Data
public class ProductDto {
    private String sku;
    private String name;
    private Double price;
}
