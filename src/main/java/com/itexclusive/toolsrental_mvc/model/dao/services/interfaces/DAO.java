package com.itexclusive.toolsrental_mvc.model.dao.services.interfaces;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> all();
    Optional<T> findById(int id);
    T save(T t);
//    T update(T t);
    boolean deleteById(int id);
}
