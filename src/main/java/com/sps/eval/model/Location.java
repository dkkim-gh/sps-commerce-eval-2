package com.sps.eval.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String address;
    private String city;
    private String state;
    private String zipCode;



}
