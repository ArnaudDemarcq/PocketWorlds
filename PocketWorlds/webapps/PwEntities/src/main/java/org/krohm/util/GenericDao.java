package org.krohm.util;

import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author beatriz
 */
public abstract class GenericDao<T, K> {
    //protected abstract EntityManager getEntityManager();
    //@PersistenceContext//(unitName="central")
    //protected EntityManager em;

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDao.class);
    // Those are not really requested, only there to manage java poor
    // generics reflexivity
    private Class clazzT;
    private Class clazzK;

    abstract protected EntityManager getEm();

    public GenericDao(Class<T> clazzT, Class<K> clazzK) {
        this.clazzT = clazzT;
        this.clazzK = clazzK;
    }

    public GenericDao(Class<T> clazz) {
        this.clazzT = clazzT;
        this.clazzK = Object.class;
    }

    public GenericDao() {
    }

    public T findById(Class<T> typeClass, K id) {
        return (T) getEm().find(typeClass, id);
    }

    public T findById(K id) {
        return (T) getEm().find(clazzT, id);
    }

    public void flush() {
        getEm().flush();
    }

    public void create(T o) {
        getEm().persist(o);
    }

    //@Transactional
    public void update(T o) {
        getEm().merge(o);
    }

    //@Transactional
    public void delete(Class<T> typeClass, K id) {
        Object o = getEm().getReference(typeClass, id);
        getEm().remove(o);
    }
}
