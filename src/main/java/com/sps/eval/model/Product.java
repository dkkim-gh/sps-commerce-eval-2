package com.sps.eval.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;


@Data
@Entity(name = "product")
public class Product {

    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true)
    private String name;
    private String description;


    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="product_subproduct",
            joinColumns = @JoinColumn( name="product_id"),
            inverseJoinColumns = @JoinColumn( name="subproduct_id")
    )
    private List<Product> subproducts;


    private double price;


}
