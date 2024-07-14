package com.example.demo.listener;

import com.example.demo.event.ProductCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProductCreatedListener implements ApplicationListener<ProductCreatedEvent> {
    @Override
    public void onApplicationEvent(ProductCreatedEvent event) {
        System.out.println("Создан новый товар: " + event.getProduct().getName());
        // Уведомление
    }
}
