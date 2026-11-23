package com.example.order.dto;
import lombok.Data;

@Data
public class PaymentDto {
    private Long orderId;
    private Double amount;
    private String cardMode;
    private String status;
}
