package pukteam.course.spring.taxes.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pukteam.course.spring.taxes.calculator.TaxCalculator;
import pukteam.course.spring.taxes.calculator.TaxCalculatorImpl;
import pukteam.course.spring.taxes.model.Person;

public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

        TaxCalculator calculator = ctx.getBean("calculator", TaxCalculator.class);

        Person menash = ctx.getBean("menash", Person.class);
        Person tikva = ctx.getBean("tikva", Person.class);
        Person simcha = ctx.getBean("simcha", Person.class);

        int taxRate = calculator.calc(menash);
        logger.info("Menash has income of " + menash.getIncome() + " and needs to pay tax: " + taxRate);

        taxRate = calculator.calc(tikva);
        logger.info("Tikva has income of " + tikva.getIncome() + " and needs to pay tax: " + taxRate);

        taxRate = calculator.calc(simcha);
        logger.info("Simcha has income of " + simcha.getIncome() + " and needs to pay tax: " + taxRate);
    }
}
