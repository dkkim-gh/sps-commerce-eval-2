package com.sps.eval.model;


import lombok.Data;

import java.util.List;

@Data
public class Organization {


    private String id;
    private String name;
    private Location location;

    private List<Subscription> subscriptions;

}
