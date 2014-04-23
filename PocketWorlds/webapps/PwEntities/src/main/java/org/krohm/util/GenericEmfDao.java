/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Arnaud
 */
public class GenericEmfDao<Key, Entity> {

    private EntityManagerFactory emf;
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericEmfDao.class);

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private Class clazzKey;
    private Class clazzEntity;

    public GenericEmfDao(Class<Key> clazzKey, Class<Entity> clazzEntity) {
        this.clazzKey = clazzKey;
        this.clazzEntity = clazzEntity;
        LOGGER.info("Dao created for class: <" + this.clazzEntity + "> and key: <" + this.clazzKey + ">");
    }

    public GenericEmfDao(Class<Entity> clazzEntity) {
        this.clazzEntity = clazzEntity;
        this.clazzKey = Object.class;
    }

    public GenericEmfDao() {
    }

    protected final EntityManager getEm() {
        return this.emf.createEntityManager();
    }

    protected final EntityManagerFactory getEmf() {
        return this.emf;
    }

    public final Entity findById(Class<Entity> typeClass, Key id) {
        EntityManager em = getEm();
        try {
            return em.find(typeClass, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public final Entity findById(Key id) {
        EntityManager em = getEm();
        try {
            return (Entity) em.find(clazzEntity, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public final void flush() {
        EntityManager em = getEm();
        try {
            em.flush();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public final void create(Entity o) {
        EntityManager em = getEm();
        try {
            em.persist(o);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public final void update(Entity o) {
        EntityManager em = getEm();
        try {
            em.merge(o);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public final void delete(Class<Entity> typeClass, Key id) {
        EntityManager em = getEm();
        try {
            Object o = em.find(typeClass, id);
            em.remove(o);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
