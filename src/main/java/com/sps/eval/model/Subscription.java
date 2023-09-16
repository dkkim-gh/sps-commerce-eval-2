package com.sps.eval.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Data
@Entity
public class Subscription {


    @Id
    @UuidGenerator
    private String id;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="organization_id", nullable=false)
    private Organization organization;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Product> products;
    private double discount;

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
