package net.ivica.reservations.service;

import net.ivica.reservations.api.Identifiable;
import net.ivica.reservations.api.Product;
import net.ivica.reservations.api.command.AbstractSearchCommand;
import net.ivica.reservations.api.dao.GenericDao;
import net.ivica.reservations.api.service.GenericService;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractGenericService<T extends Identifiable, R extends AbstractSearchCommand>
        implements GenericService<T, R> {

    @Override
    @Transactional
    public void clearSession() {

    }

    @Override
    @Transactional
    public void flushSession() {

    }

    @Override
    @Transactional
    public void delete(T entity) {
        getEntityDao().delete(entity);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<T> findAll() {
//	return getEntityDao().findAll();
//    }

    @Override
    @Transactional(readOnly = true)
    public T findById(Long id) {
        return getEntityDao().findById(id);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<T> queryResultList(AbstractQuery<T> query) {
//	return getEntityDao().queryResultList(query);
//    }

//    @Override
//    @Transactional(readOnly = true)
//    public T querySingleResult(AbstractQuery<T> query) {
//	return getEntityDao().querySingleResult(query);
//    }

    protected abstract GenericDao<T, R> getEntityDao();

    @Override
    @Transactional
    public Long save(T entity) {
        return getEntityDao().save(entity);
    }

//    @Override
//    @Transactional
//    public PagedList<T> search(R searchCommand) {
//	return getEntityDao().search(searchCommand);
//    }

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