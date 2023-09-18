package com.sps.eval.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Subscription {


    @Id
    @UuidGenerator
    private String id;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="organization_id", nullable=false)
    private Organization organization;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Product> products;
    private double discount;

    @Transient
    public BigDecimal getTotalPrice() {

        Set<String> productNames = new HashSet();
        BigDecimal totalPrice = new BigDecimal(0.0);

        if(products.size()>0) {

            for(Product p : products) {
                for(Product subP : p.getSubproducts()) {

                    if(!productNames.contains(subP.getName())) {
                        productNames.add(subP.getName());
                        totalPrice = totalPrice.add(subP.getPrice());
                    }
                }

                if(!productNames.contains(p.getName())) {
                    productNames.add(p.getName());
                    totalPrice = totalPrice.add(p.getPrice());
                }
            }
        }

        double discountFactor = (100-getDiscount())/100;
        BigDecimal discountFactorBigDecimal = new BigDecimal(discountFactor);

        totalPrice = totalPrice.multiply(discountFactorBigDecimal);
        totalPrice = totalPrice.setScale(2, RoundingMode.HALF_EVEN);
        return totalPrice;
    }

}
