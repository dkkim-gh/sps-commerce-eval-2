package com.sps.eval.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "organization")
public class Organization {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    private String name;
    @ManyToOne
    @JoinColumn(name="location_id", nullable=true)
    private Location location;


    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private List<Subscription> subscriptions;

}
