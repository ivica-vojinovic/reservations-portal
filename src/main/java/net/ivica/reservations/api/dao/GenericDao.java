package net.ivica.reservations.api.dao;

import net.ivica.reservations.api.Identifiable;
import net.ivica.reservations.api.ParameterTuple;

import java.util.List;

public interface GenericDao<T extends Identifiable> {

    void clearSession();

    void delete(T entity);

    List<T> findAll();

    T findById(Long id);

    T findSingleResult(String queryName, ParameterTuple... parameters);

    void flushSession();

    Long save(T entity);

    void saveOrUpdate(T entity);

    void update(T entity);

}