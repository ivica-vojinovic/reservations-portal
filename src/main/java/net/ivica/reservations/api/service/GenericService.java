package net.ivica.reservations.api.service;

import net.ivica.reservations.api.Identifiable;
import net.ivica.reservations.api.command.AbstractSearchCommand;
import org.springframework.transaction.annotation.Transactional;

@Transactional
// TODO vidi kako iskomentarisane metode da resis. Moze named query-jem.
public interface GenericService<T extends Identifiable, R extends AbstractSearchCommand> {

    void clearSession();

    void delete(T entity);

    T findById(Long id);

//    List<T> findAll();

    void flushSession();

    Long save(T entity);

//    T querySingleResult(AbstractQuery<T> query);

    void saveOrUpdate(T entity);

    //    List<T> queryResultList(AbstractQuery<T> query);
//
//    List<T> queryResultList(AbstractQuery<T> query, int limit);
//
    void update(T... entity);

//    PagedListHolder<T> search(R command);

}