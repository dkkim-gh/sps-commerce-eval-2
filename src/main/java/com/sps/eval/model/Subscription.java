package com.sps.eval.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Subscription {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id", nullable=false)
    private Organization organization;

    @ManyToMany
    private List<Product> products;
    private double discount;
    //private double totalPrice;

    @Transient
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
