package pukteam.course.spring.di.impl.vehicle;

import pukteam.course.spring.di.api.Engine;
import pukteam.course.spring.di.api.Vehicle;
import pukteam.course.spring.di.api.Wheel;

import java.util.List;


public class Car implements Vehicle {

    private Engine engine;
    private List<Wheel> wheels;

    public Car(Engine engine, List<Wheel> wheels) {
        this.engine = engine;
        this.wheels = wheels;
    }

    public Engine getEngine() {
        return engine;
    }

    public List<Wheel> getWheels() {
        return wheels;
    }

    @Override
    public void drive() {
        System.out.println("Driving my super Car ! vruuumm vreuuuumm !");
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", wheels=" + wheels +
                '}';
    }
}
