package com.sps.eval.service;

import com.sps.eval.model.Subscription;
import com.sps.eval.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    public Optional<Subscription> findSubscriptionById(String id) {
        return subscriptionRepository.findById(id);
    }


    public Subscription save(Subscription subscription) {
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return savedSubscription;
    }

}
