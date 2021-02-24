package com.example.reservation.organization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class OrganizationRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    OrganizationRepository organizationRepository;


    @Test
    void when_db_is_empty_then_return_empty_organization_list() {

        List<Organization> organizationList = organizationRepository.findAll();
        assertEquals(0, organizationList.size());

    }

    @Test
    void when_db_contains_organization_with_name_org1_then_find_by_id_should_return_it() {

        Organization organization = new Organization("org1", "opis1");
        testEntityManager.persist(organization);

        Optional<Organization> optOrg = organizationRepository.findById("org1");

        assertEquals("org1", optOrg.get().getName());
        assertEquals("opis1", optOrg.get().getDescription());
    }


    @Test
    void when_db_contains_organization_with_some_description_then_find_by_description_should_return_it() {

        String descriptionPart = "IT";

        testEntityManager.persist(new Organization("org1", "org1"));
        testEntityManager.persist(new Organization("org2", "org2IT"));
        testEntityManager.persist(new Organization("org3", "IT"));

        List<Organization> itOrganizations = organizationRepository.findByDescriptionContains(descriptionPart);

        assertEquals(2, itOrganizations.size());

    }
}
