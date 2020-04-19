package com.edoras.petclinic.service.springdatajpa;

import com.edoras.petclinic.model.Owner;
import com.edoras.petclinic.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerJpaService ownerJpaService;

    private final Long ID = 1L;
    private final String LAST_NAME = "Calik";

    Owner savedOwner;

    @BeforeEach
    void setUp() {
        savedOwner = Owner.builder().id(ID).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(savedOwner);
        Owner returnOwner = ownerJpaService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, returnOwner.getLastName());
        verify(ownerRepository, Mockito.times(1)).findByLastName(any());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(savedOwner));
        Owner returnOwner = ownerJpaService.findById(ID);

        assertEquals(ID, returnOwner.getId());
        verify(ownerRepository, Mockito.times(1)).findById(any());
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).lastName("Calik").build();

        when(ownerRepository.save(any())).thenReturn(savedOwner);
        Owner ownerSaved = ownerJpaService.save(ownerToSave);

        assertNotNull(ownerSaved);
        verify(ownerRepository, Mockito.times(1)).save(any());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(2L).lastName("Gökyer").build());
        ownerSet.add(Owner.builder().id(3L).lastName("İşçi").build());

        when(ownerRepository.findAll()).thenReturn(ownerSet);
        Set<Owner> returnedOwners = ownerJpaService.findAll();

        assertNotNull(returnedOwners);
        assertEquals(2, returnedOwners.size());
    }

    @Test
    void delete() {
        ownerJpaService.delete(savedOwner);
        verify(ownerRepository, Mockito.times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerJpaService.deleteById(ID);
        verify(ownerRepository, Mockito.times(1)).deleteById(any());
    }
}