package com.makeev.DAO;

import com.makeev.model.Engine;
//import com.mysql.cj.Session;
import com.sun.istack.NotNull;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

public class EngineDAO implements DAO<Engine, Integer> {

    private final SessionFactory factory;

    public EngineDAO(@NotNull SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(@NotNull final Engine engine) {
        try(final Session session = factory.openSession()) {
            System.out.println("------");
            session.beginTransaction();
            session.save(engine);
            session.getTransaction().commit();
        }
    }

    @Override
    public Engine read(@NotNull final Integer id) {
        try(final Session session = factory.openSession()) {
        final Engine result = session.get(Engine.class, id);
        return result != null ? result : new Engine();
        }
    }

    @Override
    public void update(@NotNull final Engine engine) {
        try(final Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(engine);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(@NotNull final Engine engine) {
        try(final Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(engine);
            session.getTransaction().commit();
        }
    }
}
