package com.makeev;

import com.makeev.model.Engine;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Engine en1 = new Engine();
        Engine en2 = new Engine("mazda", 125);
        System.out.println(en2);
    }

}