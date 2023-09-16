package com.sps.eval.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "organization")
public class Organization {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String name;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="location_id", nullable=true)
    private Location location;


    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization", cascade=CascadeType.ALL)
    private List<Subscription> subscriptions;

}
