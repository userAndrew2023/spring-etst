package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.event.ProductCreatedEvent;
import com.example.demo.repository.ProductRepository;
import com.example.demo.strategy.PaymentStrategy;
import com.example.demo.strategy.YookassaPayment;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Setter
    private PaymentStrategy paymentStrategy;

    @Autowired
    public ProductService(ProductRepository productRepository, ApplicationEventPublisher eventPublisher) {
        this.productRepository = productRepository;
        this.eventPublisher = eventPublisher;
        this.paymentStrategy = new YookassaPayment(); // В сеттере можно изменить
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Product ID should be null for creation");
        }
        Product createdProduct = productRepository.save(product);
        eventPublisher.publishEvent(new ProductCreatedEvent(this, createdProduct));
        return createdProduct;
    }

    public Product updateProduct(Product product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("Product ID cannot be null for update");
        }
        return productRepository.save(product);
    }

    public String buyProduct(Product product) {
        return paymentStrategy.pay(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
