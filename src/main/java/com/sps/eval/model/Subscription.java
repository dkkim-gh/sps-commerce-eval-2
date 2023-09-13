package com.sps.eval.model;

import lombok.Data;

import java.util.List;

@Data
public class Subscription {

    private String id;
    private Organization organization;
    private List<Product> products;
    private double discount;
    private double totalPrice;


}
