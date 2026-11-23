package com.example.order.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @PostMapping("/api/inventory/deduct")
    Object deductStock(@RequestParam("sku") String sku, @RequestParam("qty") int qty);

    @PostMapping("/api/inventory/release")
    Object releaseStock(@RequestParam("sku") String sku, @RequestParam("qty") int qty);
}