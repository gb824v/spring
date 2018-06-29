package pukteam.course.spring.taxes.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pukteam.course.spring.taxes.calculator.TaxCalculator;
import pukteam.course.spring.taxes.model.Person;

import java.util.Map;

public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

        ctx.registerShutdownHook();

        logger.info("====   Starting main work   ====");
        TaxCalculator calculator = ctx.getBean("calculatorFromFactory", TaxCalculator.class);
        Map<String, Person> personList = ctx.getBean("personList", Map.class);

        personList.forEach( (name, person) -> {
            int taxRate = calculator.calc(person);
            logger.info("{} has income of {} and needs to pay tax: {}", name, person.getIncome(), taxRate);
        });
        logger.info("==========================");
        personList.forEach( (name, person) -> {
            int count = person.getCounterForIncomeRequests();
            logger.info("{}'s income has been requested {} times so far...", name, count);
        });
        logger.info("====   main is over...   ====");
    }
}
