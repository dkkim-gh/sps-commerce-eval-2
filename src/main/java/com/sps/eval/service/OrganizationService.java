package com.sps.eval.service;

import com.sps.eval.model.Organization;
import com.sps.eval.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    public Optional<Organization> getByPrimaryKey(String id) {
        return organizationRepository.findById(id);
    }

}
