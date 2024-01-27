package com.example.glovo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;

@AllArgsConstructor
@Data
@Builder
public class Product {
    private long id;
    private String category;
    private String name;
    private double price;
}
