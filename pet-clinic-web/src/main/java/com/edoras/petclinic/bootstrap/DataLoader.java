package com.edoras.petclinic.bootstrap;

import com.edoras.petclinic.model.Owner;
import com.edoras.petclinic.model.Vet;
import com.edoras.petclinic.service.OwnerService;
import com.edoras.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private OwnerService ownerService;
    private VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
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
