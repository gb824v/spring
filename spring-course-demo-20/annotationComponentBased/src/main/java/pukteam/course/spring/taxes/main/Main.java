package pukteam.course.spring.taxes.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pukteam.course.spring.taxes.calculator.TaxCalculator;
import pukteam.course.spring.taxes.calculator.factory.TaxCalculatorFactory;
import pukteam.course.spring.taxes.calculator.factory.TaxCalculatorMode;
import pukteam.course.spring.taxes.model.person.PersonRepository;

public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext("pukteam.course.spring.taxes.model", "pukteam.course.spring.taxes.limit", "pukteam.course.spring.taxes.calculator", "pukteam.course.spring.taxes.conf");
        
        ctx.registerShutdownHook();

        logger.info("====   Starting main work   ====");
        TaxCalculatorFactory westernTaxCalculatorFactory = ctx.getBean("westernTaxCalculatorFactory", TaxCalculatorFactory.class);
        TaxCalculator taxCalculator = westernTaxCalculatorFactory.obtainCalculator(TaxCalculatorMode.LIGHT);

//        TaxCalculatorFactory handmaidTaleTaxCalculatorFactory = ctx.getBean("handmaidTaleTaxCalculatorFactory", TaxCalculatorFactory.class);
//        TaxCalculator taxCalculator = handmaidTaleTaxCalculatorFactory.obtainCalculator(TaxCalculatorMode.LIGHT);

        PersonRepository personRepository = ctx.getBean("personRepository", PersonRepository.class);

        personRepository.stream().forEach( person -> {
            int taxRate = taxCalculator.calc(person);
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
