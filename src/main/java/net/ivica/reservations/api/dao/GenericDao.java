package net.ivica.reservations.api.dao;

import net.ivica.reservations.api.Identifiable;
import net.ivica.reservations.api.command.AbstractSearchCommand;

// TODO vidi kako iskomentarisane metode da resis. Moze named query-jem.
public interface GenericDao<T extends Identifiable, R extends AbstractSearchCommand> {

    void clearSession();

    void delete(T entity);

//    List<T> findAll();

    T findById(Long id);

    void flushSession();

//    List<T> queryResultList(AbstractQuery<T> query);
//
//    List<T> queryResultList(AbstractQuery<T> query, int limit);
//
//    T querySingleResult(AbstractQuery<T> query);

    Long save(T entity);

    void saveOrUpdate(T entity);

//    PagedListHolder<T> search(R command);

    void update(T entity);

}