package com.edoras.petclinic.service.map;

import com.edoras.petclinic.model.Speciality;
import com.edoras.petclinic.service.SpecialityService;

import java.util.Set;

public class SpecialityServiceMap extends AbstractServiceMap<Speciality, Long> implements SpecialityService {
    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
