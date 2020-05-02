package com.edoras.petclinic.bootstrap;

import com.edoras.petclinic.model.*;
import com.edoras.petclinic.service.OwnerService;
import com.edoras.petclinic.service.PetTypeService;
import com.edoras.petclinic.service.VetService;
import com.edoras.petclinic.service.VisitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        PetType dogType = new PetType();
        dogType.setName("Dog");
        petTypeService.save(dogType);

        PetType catType = new PetType();
        catType.setName("Cat");
        petTypeService.save(catType);

        Owner owner1 = new Owner();
        owner1.setFirstName("Emre");
        owner1.setLastName("Calik");
        owner1.setAddress("Pozcu");
        owner1.setCity("Mersin");
        owner1.setTelephone("05068862089");

        Pet emresPet = new Pet();
        emresPet.setBirthDate(LocalDate.now());
        emresPet.setOwner(owner1);
        owner1.getPets().add(emresPet);
        emresPet.setPetType(dogType);
        emresPet.setName("Tony");

        ownerService.save(owner1);

//        Visit emresPetVisit = new Visit();
//        emresPetVisit.setDate(LocalDate.now());
//        emresPetVisit.setDescription("Good old boy");
//        emresPetVisit.setPet(emresPet);
//        visitService.save(emresPetVisit);
//
//        emresPet.getVisits().add(emresPetVisit);

        Owner owner2 = new Owner();
        owner2.setFirstName("Kaan");
        owner2.setLastName("Can");
        owner2.setAddress("Nilüfer");
        owner2.setCity("Bursa");
        owner2.setTelephone("05059283921");

        Pet kaansPet = new Pet();
        kaansPet.setBirthDate(LocalDate.now());
        kaansPet.setOwner(owner2);
        owner2.getPets().add(kaansPet);
        kaansPet.setPetType(catType);
        kaansPet.setName("Gigi");

        ownerService.save(owner2);

        System.out.println("Owners have been loaded..");

        Vet vet1 = new Vet();
        vet1.setFirstName("İdil");
        vet1.setLastName("İşçi");

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Denstistry");
        vet1.getSpecialities().add(dentistry);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Yusuf");
        vet2.setLastName("Gökyer");

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        vet2.getSpecialities().add(surgery);

        vetService.save(vet2);

        System.out.println("Vets have been loaded..");
    }
}
