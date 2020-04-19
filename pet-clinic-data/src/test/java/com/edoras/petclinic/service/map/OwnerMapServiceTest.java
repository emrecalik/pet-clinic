package com.edoras.petclinic.service.map;

import com.edoras.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    Long id = 1L;
    String lastName = "Calik";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetMapService(new VisitMapService()), new PetTypeMapService());
        ownerMapService.save(Owner.builder().id(id).lastName(lastName).build());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(1L);
        assertEquals(id, owner.getId());
    }

    @Test
    void save() {
        Long id2 = 2L;
        Owner owner = Owner.builder().id(id2).build();
        Owner savedOwner = ownerMapService.save(owner);

        assertEquals(id2, savedOwner.getId());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(id));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(id);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner foundOwner = ownerMapService.findByLastName(lastName);
        assertEquals(lastName, foundOwner.getLastName());
    }
}