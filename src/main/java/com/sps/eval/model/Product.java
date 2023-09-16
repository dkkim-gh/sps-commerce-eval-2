package com.sps.eval.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String name;
    private String description;

    /*
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="product_subproduct",
            joinColumns = @JoinColumn( name="product_id"),
            inverseJoinColumns = @JoinColumn( name="subproduct_id")
    )
    private List<Product> subproducts;
    */

    private double price;


}
