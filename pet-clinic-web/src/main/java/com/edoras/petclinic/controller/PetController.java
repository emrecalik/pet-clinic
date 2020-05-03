package com.edoras.petclinic.controller;

import com.edoras.petclinic.model.Owner;
import com.edoras.petclinic.model.Pet;
import com.edoras.petclinic.model.PetType;
import com.edoras.petclinic.service.OwnerService;
import com.edoras.petclinic.service.PetService;
import com.edoras.petclinic.service.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners")
public class PetController {
    private final String CREATE_OR_UPDATE_PET_FORM = "pet/create-update-pet";

    OwnerService ownerService;
    PetService petService;
    PetTypeService petTypeService;

    public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> loadPetTypes() {
        return petTypeService.findAll();
    }

    @GetMapping("/{ownerId}/pets/new")
    public String createPetFormInit(Model model, @PathVariable Long ownerId) {
        Owner owner = ownerService.findById(ownerId);
        Pet pet = new Pet();
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/{ownerId}/pets/new")
    public String createPetFormProcess(@Valid Pet pet, @PathVariable Long ownerId) {
        Owner owner = ownerService.findById(ownerId);
        owner.getPets().add(pet);
        pet.setOwner(owner);
        petService.save(pet);
        return "redirect:/owners/" + ownerId;
    }

    @GetMapping("/{ownerId}/pets/{petId}/edit")
    public String updatePetFormInit(Model model, @PathVariable Long petId, @PathVariable Long ownerId) {
        Pet pet = petService.findById(petId);
        Owner owner = ownerService.findById(ownerId);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/{ownerId}/pets/{petId}/edit")
    public String updatePetFormProcess(@Valid Pet pet, @PathVariable Long ownerId) {
        Owner owner = ownerService.findById(ownerId);
        pet.setOwner(owner);
        petService.save(pet);
        return "redirect:/owners/" + ownerId;
    }

}
