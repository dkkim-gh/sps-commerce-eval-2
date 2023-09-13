package com.sps.eval.model;

import lombok.Data;

import java.util.List;

@Data
public class Product {

    private String id;
    private String name;
    private String description;
    private List<Product> subproducts;
    private double price;


}
