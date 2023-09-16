package com.sps.eval.service;

import com.sps.eval.model.Location;
import com.sps.eval.model.Organization;
import com.sps.eval.repository.OrganizationRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    public Optional<Organization> findOrganizationById(String id) {
        return organizationRepository.findById(id);
    }

    public Organization save(Organization organization) {
        organizationRepository.save(organization);
        return organization;
    }

    public Page<Organization> findAll(Pageable pageable) {
        return organizationRepository.findAll(pageable);
    }

}
