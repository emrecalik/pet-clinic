package com.edoras.petclinic.service.map;

import com.edoras.petclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID> {
    protected Map<Long, T> map = new HashMap<>();

    T findById(ID id) {
       return map.get(id);
    }

    T save(T object) {
        if (object != null) {
            if (object.getId() == null) {
                object.setId(generateId(object));
            }
            map.put(object.getId(), object);
            return object;
        } else {
            throw new RuntimeException("Object can not be null");
        }
    }

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    void delete(T object) {
        map.entrySet().removeIf(item -> item.getValue().equals(object));
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    long generateId(T object) {
        long id;
        try {
            id = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e) {
            id = 1;
        }
        object.setId(id);
        return id;
    }
}
