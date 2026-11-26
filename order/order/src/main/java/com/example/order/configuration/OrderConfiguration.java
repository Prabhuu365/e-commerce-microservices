package com.example.order.configuration;

import com.example.order.service.OrderService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class OrderConfiguration {
    @Bean
    @LoadBalanced
    public WebClient.Builder getwebclient(){
        return WebClient.builder();
    }

    @Bean
    @Qualifier("orderLog")
    public Logger getOrderlog(){
      return  LoggerFactory.getLogger(OrderService.class);
    }

    @Bean
    @Qualifier("webclientLog")
    public Logger getOrderWebClientLog(){
        return LoggerFactory.getLogger(WebClient.class);
    }
}
