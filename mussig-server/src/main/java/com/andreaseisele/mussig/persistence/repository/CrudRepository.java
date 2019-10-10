package com.andreaseisele.mussig.persistence.repository;

import com.andreaseisele.mussig.persistence.model.BaseObject;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toUnmodifiableList;

public interface CrudRepository<ID, E extends BaseObject<ID>> {

    Optional<E> findById(ID id);

    List<E> findAll();

    E saveOrUpdate(E entity);

    default List<E> saveAll(List<E> entities) {
        return entities.stream().map(this::saveOrUpdate).collect(toUnmodifiableList());
    }

    void delete(ID id);

}
