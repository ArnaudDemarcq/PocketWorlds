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
        LOGGER.info("Dao created for class: <" + this.clazzT + "> and key: <" + this.clazzK + ">");
    }

    public GenericDao(Class<T> clazz) {
        this.clazzT = clazzT;
        this.clazzK = Object.class;
    }

    public GenericDao() {
    }

    public final T findById(Class<T> typeClass, K id) {
        return (T) getEm().find(typeClass, id);
    }

    public final T findById(K id) {
        return (T) getEm().find(clazzT, id);
    }

    public final void flush() {
        getEm().flush();
    }

    public final void create(T o) {
        getEm().persist(o);
    }

    //@Transactional
    public final void update(T o) {
        getEm().merge(o);
    }

    //@Transactional
    public final void delete(Class<T> typeClass, K id) {
        Object o = getEm().getReference(typeClass, id);
        getEm().remove(o);
    }
}
