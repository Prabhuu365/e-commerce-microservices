package com.example.payment.service;


import com.example.payment.entity.Payment;
import com.example.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;

    public Payment charge(Payment payment) {
        if ("DECLINE".equalsIgnoreCase(payment.getCardMode())) {
            payment.setStatus("DECLINED");
        } else {
            payment.setStatus("SUCCESS");
        }
        return paymentRepo.save(payment);
    }

    public Payment getByOrderId(Long orderId) {
        return paymentRepo.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for order: " + orderId));
    }
}
