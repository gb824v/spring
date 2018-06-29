package pukteam.course.spring.taxes.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pukteam.course.spring.taxes.calculator.TaxCalculator;
import pukteam.course.spring.taxes.conf.TaxConfiguration;
import pukteam.course.spring.taxes.model.person.PersonRepository;

import java.util.Map;

public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(TaxConfiguration.class);
        
        ctx.registerShutdownHook();

        logger.info("====   Starting main work   ====");
        TaxCalculator calculator = ctx.getBean("taxes-calc-light", TaxCalculator.class);
        PersonRepository personRepository = ctx.getBean("personRepository", PersonRepository.class);

        personRepository.stream().forEach( person -> {
            int taxRate = calculator.calc(person);
            logger.info("{} has income of {} and needs to pay tax: {}", person.getName(), person.getIncome(), taxRate);
        });

        logger.info("==========================");
        personRepository.stream().forEach( person -> {
            int count = person.getCounterForIncomeRequests();
            logger.info("{}'s income has been requested {} times so far...", person.getName(), count);
        });

        logger.info("====   main is over...   ====");
    }
}
