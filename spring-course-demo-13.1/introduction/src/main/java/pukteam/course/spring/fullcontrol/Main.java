package pukteam.course.spring.fullcontrol;

import pukteam.course.spring.fullcontrol.vehicle.Car;
import pukteam.course.spring.fullcontrol.vehicle.CircusBicycle;
import pukteam.course.spring.fullcontrol.vehicle.Vehicle;

public class Main {
    public static void main(String[] args) {

        Vehicle car = new Car();
        CircusBicycle circusBicycle = new CircusBicycle();

        System.out.println("Car: " + car);
        car.drive();
        System.out.println("==============");
        System.out.println("Circus bicycle: " + circusBicycle);
        circusBicycle.drive();
        
    }
}
