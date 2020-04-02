package com.edoras.petclinic.bootstrap;

import com.edoras.petclinic.model.Owner;
import com.edoras.petclinic.model.PetType;
import com.edoras.petclinic.model.Vet;
import com.edoras.petclinic.service.OwnerService;
import com.edoras.petclinic.service.PetTypeService;
import com.edoras.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        PetType dogType = new PetType();
        dogType.setName("Şans");
        petTypeService.save(dogType);

        PetType catType = new PetType();
        catType.setName("Lokum");
        petTypeService.save(catType);

        Owner owner1 = new Owner();
        owner1.setFirstName("Emre");
        owner1.setLastName("Calik");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Kaan");
        owner2.setLastName("Can");
        ownerService.save(owner2);

        System.out.println("Owners have been loaded..");

        Vet vet1 = new Vet();
        vet1.setFirstName("İdil");
        vet1.setLastName("İşçi");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Yusuf");
        vet2.setLastName("Gökyer");
        vetService.save(vet2);

        System.out.println("Vets have been loaded..");
    }
}
