/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.SQL;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    public void persist(T entity) {
        Session session = getSession();
        getSession().persist(entity);
        session.flush();
        session.clear();
    }

    public void update(T entity) {
        Session session = getSession();
        session.update(entity);
        session.flush();
        session.clear();
    }

    public void update(List<T> entity) {
        Session session = getSession();
        for (T e : entity) {
            session.update(e);
        }
        session.flush();
        session.clear();
    }


    public void add(List<T> entity) {
        Session session = getSession();
        for (T e : entity) {
            session.save(e);
        }
        session.flush();
        session.clear();
    }

    public void flush(T entity) {
        getSession().getTransaction().commit();
    }

    public void delete(T entity) {
        Session session = getSession();
        session.delete(entity);
        session.flush();
        session.clear();
    }

    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }


}
