package com.edoras.petclinic.service.map;

import com.edoras.petclinic.model.PetType;
import com.edoras.petclinic.service.PetTypeService;

import java.util.Set;

public class PetTypeServiceMap extends AbstractServiceMap<PetType, Long> implements PetTypeService {
    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }

    @Override
    public PetType save(PetType object) {
        return super.save(object);
    }

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(PetType object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}