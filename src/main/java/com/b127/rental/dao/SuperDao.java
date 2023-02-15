package com.b127.rental.dao;

import java.util.List;
import java.util.Optional;

public interface SuperDao<T> {

    boolean save(T t);

    boolean update(T t, long id);

    Optional<T> getById(long id);

    boolean delete(long id);

    List<T> getAll();
}
