package pukteam.course.spring.autowire;

import org.apache.logging.log4j.LogManager;

public class B {

    private C c;

    public void setC(C c) {
        this.c = c;
    }

    public void doSomething() {
        LogManager.getLogger(B.class).info("at B class. calling C...");
        c.doSomething();
    }
}
