package pukteam.course.spring.taxes.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pukteam.course.spring.taxes.calculator.TaxCalculator;
import pukteam.course.spring.taxes.conf.TaxConfiguration;
import pukteam.course.spring.taxes.model.Person;

import java.util.Map;

public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(TaxConfiguration.class);

        logger.info("====   Starting main work   ====");
        TaxCalculator calculator = ctx.getBean("taxCalculator", TaxCalculator.class);
        Map<String, Person> personMap = ctx.getBean("personMap", Map.class);

        personMap.forEach( (name, person) -> {
            int taxRate = calculator.calc(person);
            logger.info("{} has income of {} and needs to pay tax: {}", name, person.getIncome(), taxRate);
        });

        logger.info("====   main is over...   ====");
    }
}
