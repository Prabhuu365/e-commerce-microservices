package com.example.inventory.service;


import com.example.inventory.entity.Inventory;
import com.example.inventory.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {
    private final static Logger log = LoggerFactory.getLogger(InventoryService.class);

    @Autowired
    private InventoryRepository inventoryRepo;

    public Inventory addStock(Inventory inventory) {
        log.info("Stock is being added for ProductSku={}",inventory.getSku());
        Inventory savedInventory = inventoryRepo.save(inventory);
        log.info("Inventory has been created for product={} with inventory id={}",savedInventory.getSku(),savedInventory.getId());
        return savedInventory;
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
