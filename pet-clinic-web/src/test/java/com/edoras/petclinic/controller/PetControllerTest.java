package com.edoras.petclinic.controller;

import com.edoras.petclinic.model.Owner;
import com.edoras.petclinic.model.Pet;
import com.edoras.petclinic.service.OwnerService;
import com.edoras.petclinic.service.PetService;
import com.edoras.petclinic.service.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PetControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    PetService petService;

    @Mock
    PetTypeService petTypeService;

    PetController petController;

    MockMvc mockMvc;

    private final String CREATE_OR_UPDATE_PET_FORM = "pet/create-update-pet";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        petController = new PetController(ownerService, petService, petTypeService);
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void loadPetTypes() {
        // todo
    }

    @Test
    void createPetFormInit() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(CREATE_OR_UPDATE_PET_FORM))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("types"));

        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void createPetFormProcess() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());
        when(petService.save(any())).thenReturn(Pet.builder().build());

        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService, times(1)).save(any());
    }

    @Test
    void updatePetFormInit() throws Exception {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().build());
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1/pets/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(CREATE_OR_UPDATE_PET_FORM))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("types"));
    }

    @Test
    void updatePetFormProcess() throws Exception {
        mockMvc.perform(post("/owners/1/pets/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + 1));

        verify(petService, times(1)).save(any());
    }
}