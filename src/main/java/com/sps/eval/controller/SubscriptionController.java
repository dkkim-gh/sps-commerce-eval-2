package com.sps.eval.controller;

import com.sps.eval.message.ResponseMessage;
import com.sps.eval.model.Organization;
import com.sps.eval.model.Subscription;
import com.sps.eval.service.SubscriptionService;
import org.springdoc.core.annotations.ParameterObject;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable String id) {

        Optional<Subscription> subscriptionOptional = subscriptionService.findSubscriptionById(id);

        if(subscriptionOptional.isPresent()) {
            return new ResponseEntity<>(subscriptionOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<Subscription> saveSubscription(@RequestBody Subscription subscription) {
        subscriptionService.save(subscription);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }


    @GetMapping(value = "/all")
    @PageableAsQueryParam
    public ResponseEntity<Page<Subscription>> findAllLocations(@ParameterObject Pageable pageable) {
        Page<Subscription> page = subscriptionService.findAll(pageable);
        return new ResponseEntity(page, HttpStatus.OK);
    }


    @PostMapping(value = "/upload")
    public ResponseEntity<ResponseMessage> uploadSubscriptionCsvFile(@RequestParam("file") MultipartFile file) {
        List<Subscription> uploadedSubscriptions = subscriptionService.importSubscriptionCsvFile(file);
        ResponseMessage responseMessage = new ResponseMessage("Processed " + uploadedSubscriptions.size() + " subscriptions.");
        return new ResponseEntity(responseMessage, HttpStatus.OK);
    }

}
