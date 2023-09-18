package com.sps.eval.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "organization")
public class Organization {


    @Id
    @UuidGenerator
    private String id;
    private String name;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="location_id", nullable=true)
    private Location location;


    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization", cascade=CascadeType.ALL)
    private List<Subscription> subscriptions;

}
