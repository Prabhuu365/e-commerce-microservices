package com.example.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InventoryResponseDto {
    private Long id;
    private String sku;
    private Integer availableQty;
}
