package com.example.order.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders") // "order" is a reserved SQL keyword
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    private Integer qty;
    private Double totalAmount;
    private String cardMode; // "OK" or "DECLINE"
    private String status;   // PENDING, CONFIRMED, FAILED
}
