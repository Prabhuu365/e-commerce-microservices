package com.example.order.client;

import com.example.order.dto.InventoryResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class OrderWebClient {

    private final WebClient webclient;

    public OrderWebClient(WebClient.Builder builder){
        this.webclient=builder.build();
    }


    public InventoryResponseDto deductinventory(String sku,int qty){
           return   webclient.get()
                   .uri("http://inventory-service//api/inventory/deduct?sku={sku}&qty",sku,qty)
                   .retrieve()
                   .bodyToMono(InventoryResponseDto.class)
                   .block();
    }
}
