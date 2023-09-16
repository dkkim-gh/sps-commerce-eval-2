package com.sps.eval.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    private String address;
    private String city;
    private String state;
    private String zipCode;



}
