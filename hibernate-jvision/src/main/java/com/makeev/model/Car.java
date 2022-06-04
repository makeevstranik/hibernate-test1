package com.makeev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private int id;
    private String mark;
    private String model;
    private Engine engine;

    public Car(String mark, String model, Engine engine) {
        this.mark = mark;
        this.model = model;
        this.engine = engine;
    }
}
