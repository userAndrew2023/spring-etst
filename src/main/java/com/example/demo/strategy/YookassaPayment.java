package com.example.demo.strategy;

import com.example.demo.entity.Product;

public class YookassaPayment implements PaymentStrategy {
    @Override
    public String pay(Product product) {
        // Post запрос в юкассу
        return "https://yookassa.ru/payment-bla-bla";
    }
}
