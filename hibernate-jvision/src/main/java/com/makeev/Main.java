package com.makeev;

import com.makeev.DAO.DAO;
import com.makeev.DAO.EngineDAO;
import com.makeev.model.Car;
import com.makeev.model.Engine;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            DAO<Engine, Integer> engineDAO = new EngineDAO(factory);
            Engine engine = engineDAO.read(1);
            System.out.println(engine);

            engine.setCarMark("honda-modern");
            engineDAO.update(engine);

            HashSet<Car> cars = new HashSet<>();
            cars.add(new Car("mazda", 23900));
            cars.add(new Car("mazda", 111111));
            engineDAO.create(new Engine("mazda-power200", 236, "mazda", cars));


        }
    }

}