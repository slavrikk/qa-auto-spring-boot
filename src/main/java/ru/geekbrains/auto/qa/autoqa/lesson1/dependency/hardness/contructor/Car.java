package ru.geekbrains.auto.qa.autoqa.lesson1.dependency.hardness.contructor;

import ru.geekbrains.auto.qa.autoqa.lesson1.dependency.hardness.Engine;

public class Car {

    private Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public static void main(String[] args) {
        Car car = new Car(new Engine());
    }
}
