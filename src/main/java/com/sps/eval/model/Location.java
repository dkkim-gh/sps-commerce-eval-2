package com.sps.eval.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
