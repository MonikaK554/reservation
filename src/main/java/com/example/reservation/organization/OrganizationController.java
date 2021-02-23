package com.example.reservation.organization;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {


    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    public List<Organization> getAll() {
        return organizationService.getAllOrganizations();
    }

    @PostMapping
    public Organization addOrganization(@RequestBody Organization organization) {
        return organizationService.addOrganization(organization);
    }

    @DeleteMapping("/{id}")
    public Organization removeOrganization(@PathVariable String id) {
        return organizationService.removeOrganization(id);
    }
}
