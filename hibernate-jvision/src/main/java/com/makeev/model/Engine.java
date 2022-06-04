package com.makeev.model;

import lombok.*;

/**
 * Author : Makeev Evgeny
 * Created 03/06/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Engine {
    private int id;
    private String model;
    private int power;

    public Engine(String model, int power) {
        this.model = model;
        this.power = power;
    }
}
