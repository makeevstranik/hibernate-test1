package com.makeev.DAO;

import com.makeev.model.Car;
import com.makeev.model.Engine;
import com.sun.istack.NotNull;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CarDAO implements DAO<Car, Integer> {
    private final SessionFactory factory;

    public CarDAO(@NotNull SessionFactory factory) {
        this.factory = factory;
    }
    @Override
    public Car read(@NotNull final Integer id) {
        try(final Session session = factory.openSession()) {
            final Car result = session.get(Car.class, id);
            //return result != null ? result : new Car();

            // for nested objects
            if (result != null) {
                Hibernate.initialize(result.getEngines());
            }
            return result;
        }
    }
}
