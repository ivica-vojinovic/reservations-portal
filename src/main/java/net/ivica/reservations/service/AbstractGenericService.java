package net.ivica.reservations.service;

import net.ivica.reservations.api.Identifiable;
import net.ivica.reservations.api.dao.GenericDao;
import net.ivica.reservations.api.service.GenericService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractGenericService<T extends Identifiable>
        implements GenericService<T> {

    @Override
    @Transactional
    public void clearSession() {

    }

    @Override
    @Transactional
    public void delete(T entity) {
        getEntityDao().delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return getEntityDao().findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(Long id) {
        return getEntityDao().findById(id);
    }

    @Override
    @Transactional
    public void flushSession() {

    }

    protected abstract GenericDao<T> getEntityDao();

    @Override
    @Transactional
    public Long save(T entity) {
        return getEntityDao().save(entity);
    }

    @Override
    @Transactional
    public void saveOrUpdate(T entity) {
        getEntityDao().saveOrUpdate(entity);
    }

    @Override
    @Transactional
    public void update(T... entities) {
        for (T entity : entities) {
            getEntityDao().update(entity);
        }
    }

}