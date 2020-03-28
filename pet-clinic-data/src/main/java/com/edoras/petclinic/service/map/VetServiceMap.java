package com.edoras.petclinic.service.map;

import com.edoras.petclinic.model.Vet;
import com.edoras.petclinic.service.CrudService;

import java.util.Set;

public class VetServiceMap extends AbstractServiceMap<Vet, Long> implements CrudService<Vet, Long> {
    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Long id, Vet object) {
        return super.save(id, object);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
