package pukteam.course.spring.autowire;

import org.apache.logging.log4j.LogManager;

public class C {

        public void doSomething() {
        LogManager.getLogger(C.class).info("at C class. nothing else left to be done...");
    }
}
