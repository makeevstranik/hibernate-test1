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
    private int cost;

    public Car(String mark, int cost) {
        this.mark = mark;
        this.cost = cost;
    }
}
