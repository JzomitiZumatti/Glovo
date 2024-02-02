package com.example.glovo.service;

import com.example.glovo.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class ProductService {
    private static final Map<Long, Product> products = new HashMap<>();
    private static long productIdCounter = 0;

    public Product add(Product product) {
        product.setId(productIdCounter++);
        products.put(product.getId(), product);
        return product;
    }

    public Product getProductId(long id) {
        return Optional.ofNullable(products.get(id))
                .orElseThrow(() -> new NoSuchElementException("Product with ID " + id + " does not exist"));
    }
}
