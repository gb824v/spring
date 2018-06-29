package pukteam.course.spring.di.impl.container;

import pukteam.course.spring.di.api.Engine;
import pukteam.course.spring.di.api.Vehicle;
import pukteam.course.spring.di.api.Wheel;
import pukteam.course.spring.di.impl.engine.V8Engine;
import pukteam.course.spring.di.impl.vehicle.Car;
import pukteam.course.spring.di.impl.vehicle.CircusBicycle;
import pukteam.course.spring.di.impl.wheel.RaceWheel;
import pukteam.course.spring.di.impl.wheel.TerrainWheel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Container {

    public static Vehicle getCar() {
        Engine v8 = new V8Engine(2000);
        List<Wheel> wheels = Stream.generate((RaceWheel::new))
                .peek(wheel -> {
                    wheel.setPressure(30);
                    wheel.setSize(40);
                })
                .limit(4)
                .collect(Collectors.toList());

        return new Car(v8, wheels);
    }

    public static Vehicle getBicycle() {
        List<Wheel> wheels = new ArrayList<>();

        Wheel front = new TerrainWheel();
        front.setSize(60);
        front.setPressure(30);
        wheels.add(front);

        Wheel rear = new RaceWheel();
        rear.setSize(10);
        rear.setPressure(35);
        wheels.add(rear);

        return new CircusBicycle(front, rear);
    }

}
