package pukteam.course.spring.springway;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pukteam.course.spring.di.impl.vehicle.Car;
import pukteam.course.spring.di.impl.vehicle.CircusBicycle;

public class SpringRunner {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext
                ("ApplicationContext.xml");

        Car car = ctx.getBean("RaceCar", Car.class);
        // Car Jeep = ctx.getBean("Jeep", Car.class);
        CircusBicycle circusBicycle = ctx.getBean("CircusBicycle", CircusBicycle.class);

        System.out.println("Car: " + car);
        car.drive();
        System.out.println("==============");
        System.out.println("Circus bicycle: " + circusBicycle);
        circusBicycle.drive();
    }
}
