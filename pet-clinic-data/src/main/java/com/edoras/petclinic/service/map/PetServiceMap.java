package com.edoras.petclinic.service.map;

import com.edoras.petclinic.model.Pet;
import com.edoras.petclinic.service.CrudService;

import java.util.Set;

public class PetServiceMap extends AbstractServiceMap<Pet, Long> implements CrudService<Pet, Long> {

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Long id, Pet object) {
        return super.save(id, object);
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
