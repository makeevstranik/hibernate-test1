package com.makeev;

import com.makeev.DAO.DAO;
import com.makeev.DAO.EngineDAO;
import com.makeev.model.Engine;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Engine en1 = new Engine();
        Engine en2 = new Engine("mazda", 125);
        System.out.println(en2);

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            DAO<Engine, String> engineDAO = new EngineDAO(factory);
            System.out.println("here");
            engineDAO.create(en2);
            Engine enFromDB = engineDAO.read("mazda");
            System.out.println(enFromDB);
            en2.setPower(10500);
            engineDAO.update(en2);
            engineDAO.delete(en2);
        }
    }

}