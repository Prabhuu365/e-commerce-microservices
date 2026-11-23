package com.example.payment.controller;


import com.example.payment.entity.Payment;
import com.example.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/charge")
    public Payment processPayment(@RequestBody Payment payment) {
        return paymentService.charge(payment);
    }

    @GetMapping("/order/{orderId}")
    public Payment getByOrder(@PathVariable Long orderId) {
        return paymentService.getByOrderId(orderId);
    }
}
