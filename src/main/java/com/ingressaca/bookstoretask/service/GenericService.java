package com.ingressaca.bookstoretask.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, E> {
    T save(T dto);

    List<T> findAll();

    T findById(Long id);

    Optional<T> update(Long id, T dto);

    void delete(Long id);

}
