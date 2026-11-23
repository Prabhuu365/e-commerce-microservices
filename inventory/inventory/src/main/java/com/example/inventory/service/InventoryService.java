package com.example.inventory.service;


import com.example.inventory.entity.Inventory;
import com.example.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepo;

    public Inventory addStock(Inventory inventory) {
        return inventoryRepo.save(inventory);
    }

    public Inventory getBySku(String sku) {
        return inventoryRepo.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("SKU not found: " + sku));
    }

    @Transactional
    public Inventory deductStock(String sku, int qty) {
        Inventory item = getBySku(sku);
        if (item.getAvailableQty() < qty) {
            throw new RuntimeException("Not enough stock for: " + sku);
        }
        item.setAvailableQty(item.getAvailableQty() - qty);
        return inventoryRepo.save(item);
    }

    @Transactional
    public Inventory releaseStock(String sku, int qty) {
        Inventory item = getBySku(sku);
        item.setAvailableQty(item.getAvailableQty() + qty);
        return inventoryRepo.save(item);
    }
}
