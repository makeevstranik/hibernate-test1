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
    public Engine read(@NotNull final Integer id) {
        try(final Session session = factory.openSession()) {
        final Engine result = session.get(Engine.class, id);
        return result != null ? result : new Engine();
        }
    }


}
