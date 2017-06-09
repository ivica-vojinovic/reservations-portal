package net.ivica.reservations.dao;

import net.ivica.reservations.api.Identifiable;
import net.ivica.reservations.api.dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public abstract class AbstractGenericDao<T extends Identifiable>
        extends HibernateDaoSupport implements GenericDao<T> {

    private Class<T> _entityPersistanceClass;

    public AbstractGenericDao(Class<T> entityPersistenceClass) {
        _entityPersistanceClass = entityPersistenceClass;
    }

    @Override
    public void clearSession() {
        getCurrentSession().clear();
    }

    @Autowired
    public void configureSessionFactory(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<T> findAll() {
        return getHibernateTemplate().loadAll(getEntityPersistanceClass());
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(Long id) {
        return (T) getCurrentSession().get(getEntityPersistanceClass(), id);
    }

    @Override
    public void flushSession() {
        getCurrentSession().flush();
    }

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    private Class<T> getEntityPersistanceClass() {
        return _entityPersistanceClass;
    }

    @Override
    public Long save(T entity) {
        return (Long) getCurrentSession().save(entity);

    }

    @Override
    public void saveOrUpdate(T entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void update(T entity) {
        getCurrentSession().update(entity);
    }

}