package com.makeev.DAO;
import com.makeev.model.Car;
import com.makeev.model.Engine;
import com.sun.istack.NotNull;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CarDAO implements DAO<Car, Integer> {
    private final SessionFactory factory;

    public CarDAO(@NotNull final SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(@NotNull final Car car) {
        try(final Session session = factory.openSession()) {
            System.out.println("------");
            session.beginTransaction();
            session.save(car);
            session.getTransaction().commit();
        }
    }

    @Override
    public Car read(@NotNull final Integer id) {
        try(final Session session = factory.openSession()) {
            final Car result = session.get(Car.class, id);
    // for nested objects
            if (result != null) {
                Hibernate.initialize(result.getEngine());
            }
            return result;
        }
    }

    @Override
    public void update(@NotNull final Car car) {
        try(final Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(car);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(@NotNull final Car car) {
        try(final Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(car);
            session.getTransaction().commit();
        }
    }
}
