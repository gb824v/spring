package pukteam.course.spring.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowireMain {
    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
        A a = ctx.getBean("a", A.class);
        a.doSomething();
    }
}
