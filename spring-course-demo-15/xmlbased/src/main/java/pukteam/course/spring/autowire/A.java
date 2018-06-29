package pukteam.course.spring.autowire;

import org.apache.logging.log4j.LogManager;

public class A {

    private B b;

    public A(B b) {
        this.b = b;
    }

    public void doSomething() {
        LogManager.getLogger(A.class).info("at A class. calling B...");
        b.doSomething();;
    }
}
