package com.sps.eval.service;

import com.sps.eval.model.Location;
import com.sps.eval.model.Subscription;
import com.sps.eval.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    public Optional<Subscription> findSubscriptionById(String id) {
        return subscriptionRepository.findById(id);
    }


    public Subscription save(Subscription subscription) {
        subscriptionRepository.save(subscription);
        return subscription;
    }

    public Page<Subscription> findAll(Pageable pageable) {
        return subscriptionRepository.findAll(pageable);
    }

    public List<Subscription> importSubscriptionCsvFile(MultipartFile file) {

        // TODO implementation of this feature was not part of the assessment.
        // "API should support the import
        // of Subscriptions in CSV/XLSX format up to 2GB in size. It's not part of this
        // exercise to process import itself, as a requirement processing time might
        // exceed request timeout for this API."
        // This could potentially be done asynchronously

        return new ArrayList<>();
    }

}
