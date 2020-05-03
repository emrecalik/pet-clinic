package com.edoras.petclinic.controller;

import com.edoras.petclinic.model.Pet;
import com.edoras.petclinic.service.PetService;
import com.edoras.petclinic.service.VisitService;
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

class VisitControllerTest {

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    VisitController visitController;

    MockMvc mockMvc;

    private final String CREATE_OR_UPDATE_VISIT_FORM = "pet/create-update-visit";

    private final String VISIT_URL = "/owners/1/pets/1/visits/new";

    private final String REDIRECT_URL = "redirect:/owners/1";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        visitController = new VisitController(petService, visitService);
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
        when(petService.findById(anyLong())).thenReturn(Pet.builder().build());
    }

    @Test
    void createVisitFormInit() throws Exception {
        mockMvc.perform(get(VISIT_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(CREATE_OR_UPDATE_VISIT_FORM))
                .andExpect(model().attributeExists("visit"))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    void createVisitFormProcess() throws Exception {
        mockMvc.perform(post(VISIT_URL))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_URL))
                .andExpect(model().attributeExists("pet"));

        verify(visitService, times(1)).save(any());
    }
}