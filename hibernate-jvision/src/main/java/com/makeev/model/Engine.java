package com.makeev.model;

import lombok.*;

import java.util.Set;

/**
 * Author : Makeev Evgeny
 * Created 03/06/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Engine {
    private int id;
    private String name;
    private int power;
    private String carMark;
    private Set<Car> cars;

    public Engine(String name, int power, String carMark, Set<Car> cars) {
        this.name = name;
        this.power = power;
        this.carMark = carMark;
        this.cars = cars;
    }
}
