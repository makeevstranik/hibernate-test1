package com.makeev.model;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@EqualsAndHashCode(exclude = "engines")
@ToString
public class Car {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String mark;

    @Getter
    @Setter
    private int cost;

    @Getter
    @Setter
    private Set<Engine> engines;

    public Car(String mark, int cost) {
        this.mark = mark;
        this.cost = cost;
    }
}
