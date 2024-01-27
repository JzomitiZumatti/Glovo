package com.example.glovo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class Order {
    private long id;
    private String customerName;
    private String customerNumber;
    private String address;
    private List<Product> products;
    private double totalPrice;
}
