package com.edoras.petclinic.repository;

import com.edoras.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);
}
