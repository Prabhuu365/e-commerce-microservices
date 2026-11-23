package com.example.inventory.controller;

import com.example.inventory.entity.Inventory;
import com.example.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public Inventory addStock(@RequestBody Inventory inventory) {
        return inventoryService.addStock(inventory);
    }

    @GetMapping("/{sku}")
    public Inventory getStock(@PathVariable String sku) {
        return inventoryService.getBySku(sku);
    }

    @PostMapping("/deduct")
    public Inventory deduct(@RequestParam String sku, @RequestParam int qty) {
        return inventoryService.deductStock(sku, qty);
    }

    @PostMapping("/release")
    public Inventory release(@RequestParam String sku, @RequestParam int qty) {
        return inventoryService.releaseStock(sku, qty);
    }
}