package com.makeev.model;

import lombok.*;

import java.util.Set;

/**
 * Author : Makeev Evgeny
 * Created 03/06/22
 */

@NoArgsConstructor
@EqualsAndHashCode(exclude = "cars")
@ToString
public class Engine {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int power;

    @Getter
    @Setter
    private Set<Car> cars;

    public Engine(String name, int power,  Set<Car> cars) {
        this.name = name;
        this.power = power;
        this.cars = cars;
    }
}
