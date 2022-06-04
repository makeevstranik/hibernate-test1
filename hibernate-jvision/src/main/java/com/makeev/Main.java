package com.makeev;

import com.makeev.DAO.CarDAO;
import com.makeev.DAO.DAO;
import com.makeev.DAO.EngineDAO;
import com.makeev.model.Car;
import com.makeev.model.Engine;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        Engine en1 = new Engine();
//        Engine en2 = new Engine("mazda", 125);
//        System.out.println(en2);

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            DAO<Car, Integer> carDAO = new CarDAO(factory);
            Car carFromTable = carDAO.read(4);
            System.out.println("Read: " + carFromTable);
            EngineDAO engineDAO = new EngineDAO(factory);
            Engine engineFromTable = engineDAO.read(1);
            System.out.println("Read Engine " + engineFromTable);
//            carFromTable.getEngine().setModel("opel-vx30");
//            carFromTable.getEngine().setPower(30);
//            carDAO.update(carFromTable);
            Engine engine2 = new Engine("lifan-35", 35);
            Car car2 = new Car("gidrotor", "gtr-35", engine2);
            car2.setId(5);
            engine2.setId(3);
//            carDAO.create(car2);
            carDAO.delete(car2);


        }
    }

}