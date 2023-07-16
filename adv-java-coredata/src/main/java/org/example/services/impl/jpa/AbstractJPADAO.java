package org.example.services.impl.jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class AbstractJPADAO<T> implements GenericDAO<T> {

    private final SessionFactory sf;
    private final Class<T> modelClass;

    public AbstractJPADAO(SessionFactory sf, Class<T> modelClass) {
        this.sf = sf;
        this.modelClass = modelClass;
    }

    @Override
    public void create(T instance) {
        Session session = getSession();
        session.persist(instance);
    }

    @Override
    public void update(T instance) {
        Session session = getSession();
        session.update(instance);
    }

    @Override
    public void delete(T instance) {
        Session session = getSession();
        session.delete(instance);
    }

    @Override
    public List<T> search(T instance) {
        Session session = getSession();
        return null;
    }

    @Override
    public T getById(int id) {
        Session session = getSession();
        return null;
    }

    @Override
    public List<T> getAll() {
        Session session = getSession();
        String query = "FROM " + modelClass.getSimpleName();
        return session.createQuery(query, modelClass).getResultList();
}
    public Session getSession() {
        Session currentSession = sf.getCurrentSession();
        if (currentSession == null) {
            currentSession = sf.openSession();
        }
        return currentSession;
    }

}
