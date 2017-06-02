package net.ivica.reservations.dao;

import net.ivica.reservations.api.Identifiable;
import net.ivica.reservations.api.command.AbstractSearchCommand;
import net.ivica.reservations.api.dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public abstract class AbstractGenericDao<T extends Identifiable, R extends AbstractSearchCommand> extends HibernateDaoSupport
        implements GenericDao<T, R> {

    private Class<T> _entityPersistanceClass;
    @Autowired
    private SessionFactory _sessionFactory;

    public AbstractGenericDao(Class<T> entityPersistenceClass) {
        _entityPersistanceClass = entityPersistenceClass;
    }

    @Override
    public void clearSession() {
        getCurrentSession().clear();
    }

    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

//    @Override
//    public List<T> findAll() {
//	return listByCriteria(null, (Criterion[]) null);
//    }

    @SuppressWarnings("hiding")
    public final <T> T execute(HibernateExecuteCallback<T> callback) {
        Session session = getCurrentSession();

        return callback.executeInHibernate(session);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(Long id) {
        return (T) getCurrentSession().get(getEntityPersistanceClass(), id);
    }

//    @Override
//    public List<T> queryResultList(AbstractQuery<T> query) {
//	return query.resultList(getCurrentSession());
//    }
//
//    @Override
//    public List<T> queryResultList(AbstractQuery<T> query, int limit) {
//	return query.resultList(getCurrentSession(), limit);
//    }
//
//    @Override
//    public T querySingleResult(AbstractQuery<T> query) {
//	return query.singleResult(getCurrentSession());
//    }

    @Override
    public void flushSession() {
        getCurrentSession().flush();
    }

    @Override
    public Long save(T entity) {
        return (Long) getCurrentSession().save(entity);

    }

//    @SuppressWarnings("unchecked")
//    @Override
//    public final PagedList<T> search(final R command) {
//
//	return execute(new HibernateExecuteCallback<PagedList<T>>() {
//
//	    @Override
//	    public PagedList<T> executeInHibernate(Session session) {
//
//		PagedListQueryGenerator<T, R> queryGenerator = getQueryGenerator();
//
//		Class<T> entityClazz = getEntityPersistanceClass();
//
//		Query query = queryGenerator.createQuery(session, entityClazz, command);
//
//		List<T> elements = query.list();
//
//		afterLoadingEntites(elements, command);
//
//		return createPagedList(elements);
//	    }
//
//	});
//    }

    @Override
    public void saveOrUpdate(T entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    private SessionFactory getSessionFactoryInternal() {
        return _sessionFactory;
    }


    @Autowired
    public void configureSessionFactory() {
        setSessionFactory(getSessionFactoryInternal());
    }

    @Override
    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    private Class<T> getEntityPersistanceClass() {
        return _entityPersistanceClass;
    }

    protected abstract void afterLoadingEntites(List<T> elements, R command);

    public interface HibernateExecuteCallback<T> {
        T executeInHibernate(Session session);
    }

}