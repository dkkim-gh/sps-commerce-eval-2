package com.sps.eval.model;

import lombok.Data;

import java.util.List;

@Data
public class Subscription {

    private String id;
    private Organization organization;
    private List<Product> products;
    private double discount;
    //private double totalPrice;

    public double getTotalPrice() {

        double totalPrice = 0.0;

        if(products!=null && products.size()>0) {
            totalPrice = products.stream().
                    map(Product::getPrice).
                    reduce(0.0, Double::sum);
        }

        return totalPrice;
    }


}
