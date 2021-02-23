package com.example.reservation.organization;


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
                    throw new IllegalArgumentException("This organization already exists");
                });

        return organizationRepository.save(organization);

    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization removeOrganization(String id){

        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("No such organization to delete");
                });

        organizationRepository.delete(organization);
        return organization;



    }

}
