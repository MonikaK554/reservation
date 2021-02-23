package com.example.reservation.organization;


import com.example.reservation.organization.exception.NoOrganizationFoundException;
import com.example.reservation.organization.exception.OrganizationAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;


    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }


    public Organization addOrganization(Organization organization) {

        final String organizationName = organization.getName();

        organizationRepository.findById(organizationName)
                .ifPresent(org -> {
                    throw new OrganizationAlreadyExistsException(organizationName);
                });

        return organizationRepository.save(organization);

    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization removeOrganization(String id) {

        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoOrganizationFoundException(id);
                });

        organizationRepository.delete(organization);
        return organization;


    }

}
