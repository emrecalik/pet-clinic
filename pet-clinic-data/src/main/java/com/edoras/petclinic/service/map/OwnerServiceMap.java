package com.edoras.petclinic.service.map;

import com.edoras.petclinic.model.Owner;
import com.edoras.petclinic.service.CrudService;

import java.util.Set;

public class OwnerServiceMap extends AbstractServiceMap<Owner, Long> implements CrudService<Owner, Long> {
    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Long id, Owner object) {
        return super.save(id, object);
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
