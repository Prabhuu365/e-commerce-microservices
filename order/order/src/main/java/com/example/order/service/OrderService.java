package com.example.order.service;


import com.example.order.client.InventoryClient;
import com.example.order.client.PaymentClient;
import com.example.order.client.ProductClient;
import com.example.order.dto.PaymentDto;
import com.example.order.dto.ProductDto;
import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private PaymentClient paymentClient;

    public Order placeOrder(Order order) {
        // 1. Get real price from product-service
        ProductDto product = productClient.getProductBySku(order.getSku());
        order.setTotalAmount(product.getPrice() * order.getQty());
        order.setStatus("PENDING");
        Order savedOrder = orderRepo.save(order);

        //System.out.println(savedOrder);

        // 2. Deduct stock from inventory-service
        inventoryClient.deductStock(order.getSku(), order.getQty());

        // 3. Call payment-service
        PaymentDto payReq = new PaymentDto();
        payReq.setOrderId(savedOrder.getId());
        payReq.setAmount(savedOrder.getTotalAmount());
        payReq.setCardMode(savedOrder.getCardMode());

        PaymentDto payRes = paymentClient.charge(payReq);

        // 4. Roll back stock if payment failed
        if ("SUCCESS".equals(payRes.getStatus())) {
            savedOrder.setStatus("CONFIRMED");
        } else {
            inventoryClient.releaseStock(order.getSku(), order.getQty());
            savedOrder.setStatus("FAILED");
        }

        return orderRepo.save(savedOrder);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Order getById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }
}
