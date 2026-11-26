package com.example.product.service;

import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepo;

    public Product addProduct(Product product) {
        log.info("Product is getting added in database with sku={}",product.getSku());
        Product productsaved = productRepo.save(product);
        log.info("Product created with productid={}",productsaved.getId());
        return productsaved;
    }

    public List<Product> getAllProducts() {
        log.trace("This is a TRACE message");
        log.debug("This is a DEBUG message");
        log.info("This is an INFO message");
        log.warn("This is a WARN message");
        log.error("This is an ERROR message");
        return productRepo.findAll();
    }

    public Product getBySku(String sku) {
        return productRepo.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Product not found: " + sku));
    }

}