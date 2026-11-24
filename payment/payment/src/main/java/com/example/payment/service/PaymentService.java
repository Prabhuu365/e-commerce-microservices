package com.example.payment.service;


import com.example.payment.dto.PaymentDto;
import com.example.payment.entity.Payment;
import com.example.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;

    public Payment charge(PaymentDto payment) {
        if ("DECLINE".equalsIgnoreCase(payment.getCardMode())) {
            payment.setStatus("DECLINED");
        } else {
            payment.setStatus("SUCCESS");
        }
        Payment payment1 = new Payment();

        payment1.setOrderId(payment.getOrderId());
        payment1.setStatus(payment.getStatus());
        payment1.setAmount(payment.getAmount());
        payment1.setCardMode(payment.getCardMode());

        return paymentRepo.save(payment1);
    }

    public Payment getByOrderId(Long orderId) {
        return paymentRepo.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for order: " + orderId));
    }
}
