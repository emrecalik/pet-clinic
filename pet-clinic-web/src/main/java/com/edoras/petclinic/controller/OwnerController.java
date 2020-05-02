package com.edoras.petclinic.controller;

import com.edoras.petclinic.model.Owner;
import com.edoras.petclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public String searchOwners(Owner owner, Model model) {

        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> ownerList = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        model.addAttribute("selections", ownerList);

        if (ownerList.size() == 0) {
            return "redirect:/owners/find";
        } else if (ownerList.size() == 1) {
            return "redirect:/owners/" + ownerList.get(0).getId();
        } else {
            return "owner/owners-list";
        }
    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owner/find-owners";
    }

    @GetMapping("/{ownerId}")
    public String displayOwner(@PathVariable Long ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId);
        model.addAttribute("owner", owner);
        return "owner/owner-details";
    }


}
