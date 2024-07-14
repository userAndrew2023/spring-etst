package com.example.demo.strategy;

import com.example.demo.entity.Product;

public class TinkoffPayment implements PaymentStrategy {
    @Override
    public String pay(Product product) {
        // Post запрос в тинькофф
        return "https://tbank.ru/payment-bla-bla";
    }
}
