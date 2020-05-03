package com.edoras.petclinic.controller;

import com.edoras.petclinic.model.Owner;
import com.edoras.petclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> ownerSet;

    MockMvc mockMvc;

    private final Long OWNER_ID = 1L;

    @BeforeEach
    void setUp() {
        ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(1L).build());
        ownerSet.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();
    }

    @Test
    void searchListOwners() throws Exception {
        Owner owner1 = new Owner();
        Owner owner2 = new Owner();

        List<Owner> ownerList = new ArrayList<>();
        ownerList.add(owner1);
        ownerList.add(owner2);

        when(ownerService.findAllByLastNameLike(any())).thenReturn(ownerList);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("selections"))
                .andExpect(view().name("owner/owners-list"));
    }

    @Test
    void searchAndFindNoOwner() throws Exception {
        when(ownerService.findAllByLastNameLike(any())).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/find"));
    }

    @Test
    void searchAndFindOneOwner() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        List<Owner> ownerList = new ArrayList<>();
        ownerList.add(owner);

        when(ownerService.findAllByLastNameLike(any())).thenReturn(ownerList);

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(view().name("owner/find-owners"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void displayOwner() throws Exception {
        Owner owner = new Owner();
        owner.setId(OWNER_ID);

        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(get("/owners/" + OWNER_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/owner-details"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void createOwnerFormInit() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owner/create-update-owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void createOwnerFormProcess() throws Exception {
        when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService, times(1)).save(any());
    }

    @Test
    void updateOwnerFormInit() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/create-update-owner"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void updateOwnerFormProcess() throws Exception {
        when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService, times(1)).save(any());
    }


}