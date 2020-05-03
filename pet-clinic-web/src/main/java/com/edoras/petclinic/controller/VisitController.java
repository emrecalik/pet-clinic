package com.edoras.petclinic.controller;

import com.edoras.petclinic.model.Pet;
import com.edoras.petclinic.model.Visit;
import com.edoras.petclinic.service.PetService;
import com.edoras.petclinic.service.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/owners")
public class VisitController {

    private final String CREATE_OR_UPDATE_VISIT_FORM = "pet/create-update-visit";

    PetService petService;

    VisitService visitService;

    public VisitController(PetService petService, VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }

    @ModelAttribute("pet")
    public Pet loadPet(@PathVariable Long petId){
        Pet pet = petService.findById(petId);
        return pet;
    }

    @GetMapping("/{ownerId}/pets/{petId}/visits/new")
    public String createVisitFormInit(Model model) {
        model.addAttribute("visit", Visit.builder().build());
        return CREATE_OR_UPDATE_VISIT_FORM;
    }

    @PostMapping("/{ownerId}/pets/{petId}/visits/new")
    public String createVisitFormProcess(@Valid Visit visit, @PathVariable Long petId, @PathVariable Long ownerId) {
        Pet pet = petService.findById(petId);
        visit.setPet(pet);
        visitService.save(visit);
        return "redirect:/owners/" + ownerId;
    }
}
