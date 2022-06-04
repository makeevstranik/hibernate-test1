package com.makeev;

import com.makeev.DAO.CarDAO;
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
            DAO<Car, Integer> carDAO = new CarDAO(factory);
            Engine engine = engineDAO.read(1);
            Car car = carDAO.read(1);
            System.out.println(engine);
            System.out.println(car);



        }
    }

}