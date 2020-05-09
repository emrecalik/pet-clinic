package com.edoras.petclinic.service.map;

import com.edoras.petclinic.model.Pet;
import com.edoras.petclinic.model.Visit;
import com.edoras.petclinic.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetMapServiceTest {

    @Mock
    VisitService visitService;

    PetMapService petMapService;

    private final Long PET_ID = 1L;
    private final Long VISIT_ID = 1L;
    private final Visit DUMMY_VISIT = new Visit();
    private final Set<Visit> visitSet = new HashSet<Visit>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        petMapService = new PetMapService(visitService);
        visitSet.add(DUMMY_VISIT);
        petMapService.save(Pet.builder().id(PET_ID).visits(visitSet).build());
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(PET_ID);
        assertEquals(PET_ID, pet.getId());
    }

    @Test
    void save() {
        Pet savedPet = petMapService.save(Pet.builder().id(2L).visits(visitSet).build());
        assertEquals(savedPet.getId(), 2L);
    }

    @Test
    void findAll() {
        Set<Pet> pets = petMapService.findAll();
        assertEquals(1, pets.size());
    }

    @Test
    void delete() {
        Pet pet = petMapService.findById(PET_ID);
        petMapService.delete(pet);
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(PET_ID);
        assertEquals(0, petMapService.findAll().size());
    }
}