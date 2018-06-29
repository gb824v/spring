package pukteam.course.spring.di.impl.vehicle;

import pukteam.course.spring.di.api.Vehicle;
import pukteam.course.spring.di.api.Wheel;

import java.util.ArrayList;
import java.util.List;

public class CircusBicycle implements Vehicle {

    private List<Wheel> wheels;

    public CircusBicycle(Wheel front, Wheel rear) {
        this.wheels = new ArrayList<>();
        wheels.add(front);
        wheels.add(rear);
    }

    @Override
    public void drive() {
        System.out.println("Driving my bicycle ! Yey !!");
    }

    @Override
    public String toString() {
        return "CircusBicycle{" +
                "wheels=" + wheels +
                '}';
    }
}
