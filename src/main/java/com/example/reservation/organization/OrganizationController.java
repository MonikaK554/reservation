package com.example.reservation.organization;


import com.example.reservation.validation.Add;
import com.example.reservation.validation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {


    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    public List<OrganizationDTO> getAll() {
        return organizationService.getAllOrganizations();
    }

    @PostMapping
    public OrganizationDTO addOrganization(@Validated(Add.class) @RequestBody OrganizationDTO organization) {
        return organizationService.addOrganization(organization);
    }

    @DeleteMapping("/{id}")
    public OrganizationDTO removeOrganization(@PathVariable String id) {
        return organizationService.removeOrganization(id);
    }

    @PutMapping("/{id}")
    public OrganizationDTO updateOrganization(@PathVariable String id, @Validated(Update.class) @RequestBody OrganizationDTO organization) {
        return organizationService.updateOrganization(id, organization);
    }

    @GetMapping("/{id}")
    public OrganizationDTO getOrganizationById(@PathVariable String id) {
        return organizationService.getOrganizationById(id);
    }
}
