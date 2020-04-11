package com.edoras.petclinic.service.springdatajpa;

import com.edoras.petclinic.model.Visit;
import com.edoras.petclinic.repository.VisitRepository;
import com.edoras.petclinic.service.VisitService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class VisitJpaService implements VisitService {

    private final VisitRepository visitRepository;

    public VisitJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }
}
