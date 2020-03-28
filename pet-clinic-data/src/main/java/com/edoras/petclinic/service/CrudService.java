package com.edoras.petclinic.service;

import java.util.Set;

public interface CrudService<T, ID> {
    T findById(ID id);

    T save(ID id, T object);

    Set<T> findAll();

    void delete(T object);

    void deleteById(ID id);
}