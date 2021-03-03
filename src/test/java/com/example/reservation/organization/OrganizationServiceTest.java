package com.example.reservation.organization;

import com.example.reservation.organization.exception.NoOrganizationFoundException;
import com.example.reservation.organization.exception.OrganizationAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
class OrganizationServiceTest {

    @TestConfiguration
    static class TestOrganizationBeanProvider {

        @Bean
        public OrganizationService organizationService(OrganizationRepository organizationRepository) {
            return new OrganizationService(organizationRepository, new OrganizationTransformer(
                    new OrganizationConferenceRoomTransformer()));
        }
    }

    @MockBean
    OrganizationRepository organizationRepository;

    @Autowired
    OrganizationService organizationService;


    @Test
    void when_organization_exists_in_repo_then_find_by_id_should_return_it() {
        Mockito.when(organizationRepository.findById("org1"))
                .thenReturn(Optional.of(new Organization("org1", "desc1")));

        OrganizationDTO organization = organizationService.getOrganizationById("org1");

        assertEquals("org1", organization.getName());
        assertEquals("desc1", organization.getDescription());
    }

    @Test
    void when_organization_does_not_exist_in_repo_then_search_for_organization_by_id_should_throw_an_exception() {

        Mockito.when(organizationRepository.findById("org2"))
                .thenReturn(Optional.of(new Organization("org2", "desc2")));


        assertThrows(NoOrganizationFoundException.class, () -> {
            organizationService.getOrganizationById("org1");
        });
    }

    @Test
    void when_add_organization_which_does_not_exist_in_repo_then_it_should_be_added() {

        Organization organization = new Organization("org1", "desc1");
        Mockito.when(organizationRepository.save(organization)).thenReturn(organization);

        OrganizationDTO added = organizationService.addOrganization(new OrganizationDTO(organization.getName(), organization.getDescription()));

        Mockito.verify(organizationRepository).save(organization);
        assertEquals("org1", added.getName());
        assertEquals("desc1", added.getDescription());
    }

    @Test
    void when_try_to_add_organization_which_already_exists_in_repo_then_exception_should_be_thrown() {
        Organization organization = new Organization("org1", "desc1");
        Mockito.when(organizationRepository.findById("org1")).thenReturn(Optional.of(organization));

        assertThrows(OrganizationAlreadyExistsException.class, () -> {
            organizationService.addOrganization(new OrganizationDTO(organization.getName(), organization.getDescription()));
        });

        Mockito.verify(organizationRepository, Mockito.never()).save(organization);

    }

}
