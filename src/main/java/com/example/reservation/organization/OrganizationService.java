package com.example.reservation.organization;


import com.example.reservation.organization.exception.NoOrganizationFoundException;
import com.example.reservation.organization.exception.OrganizationAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationTransformer organizationTransformer;


    public OrganizationService(OrganizationRepository organizationRepository, OrganizationTransformer organizationTransformer) {
        this.organizationRepository = organizationRepository;
        this.organizationTransformer = organizationTransformer;
    }

    public OrganizationDTO addOrganization(OrganizationDTO organizationDTO) {

        Organization organization = organizationTransformer.toEntity(organizationDTO);

        final String organizationName = organization.getName();

        organizationRepository.findById(organizationName)
                .ifPresent(org -> {
                    throw new OrganizationAlreadyExistsException(organizationName);
                });

        return organizationTransformer.toDTO(organizationRepository.save(organization));

    }

    public List<OrganizationDTO> getAllOrganizations() {
        return organizationRepository.findAll().stream()
                .map(organizationTransformer::toDTO)
                .collect(Collectors.toList());
    }

    public OrganizationDTO removeOrganization(String id) {

        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoOrganizationFoundException(id);
                });

        organizationRepository.delete(organization);
        return organizationTransformer.toDTO(organization);
    }

    public OrganizationDTO updateOrganization(String id, OrganizationDTO organizationUpdateDTO) {
        Organization organizationUpdate = organizationTransformer.toEntity(organizationUpdateDTO);
        Organization organizationToUpdate = organizationRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoOrganizationFoundException(id);
                });
        String newOrganizationDescription = organizationUpdate.getDescription();
        if (newOrganizationDescription != null) {
            organizationToUpdate.setDescription(newOrganizationDescription);
        }
        return organizationTransformer.toDTO(organizationRepository.save(organizationToUpdate));
    }


    public OrganizationDTO getOrganizationById(String id) {
        return organizationTransformer.toDTO(organizationRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoOrganizationFoundException(id);
                }));
    }

}
