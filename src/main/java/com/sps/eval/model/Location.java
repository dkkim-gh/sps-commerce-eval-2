package com.sps.eval.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;


@Data
@Entity(name = "location")
public class Location {

    @Id
    @UuidGenerator
    private String id;
    private String address;
    private String city;
    private String state;
    private String zipCode;



}
