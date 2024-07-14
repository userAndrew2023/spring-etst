package com.example.demo.strategy;

import com.example.demo.entity.Product;

public interface PaymentStrategy {
    String pay(Product product);
}
