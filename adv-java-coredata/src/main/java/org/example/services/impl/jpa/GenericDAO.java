package org.example.services.impl.jpa;


import java.util.List;

public interface GenericDAO<T> {
    void create(T instance);

    void update(T instance);

    void delete(T instance);

    List<T> search(T instance);


    T getById(int id);

    List<T> getAll();
}
