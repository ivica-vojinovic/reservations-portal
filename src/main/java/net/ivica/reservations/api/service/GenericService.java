package net.ivica.reservations.api.service;

import net.ivica.reservations.api.Identifiable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GenericService<T extends Identifiable> {

    void clearSession();

    void delete(T entity);

    T findById(Long id);

    List<T> findAll();

    void flushSession();

    Long save(T entity);

    void saveOrUpdate(T entity);

    void update(T... entity);

}