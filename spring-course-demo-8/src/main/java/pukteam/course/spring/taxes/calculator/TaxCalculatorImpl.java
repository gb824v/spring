package pukteam.course.spring.taxes.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import pukteam.course.spring.taxes.model.Gender;
import pukteam.course.spring.taxes.model.Person;

import java.util.*;

public class TaxCalculatorImpl implements TaxCalculator, BeanNameAware, ApplicationContextAware {

    private static Logger logger = LogManager.getLogger(TaxCalculatorImpl.class);
    private List<TaxLimit> taxLimits;
    private int boost;
    private String beanName;
    private ApplicationContext applicationContext;

    public TaxCalculatorImpl(List<TaxLimit> taxLimits, int boost) {
        this.taxLimits = taxLimits;
        this.boost = boost;
    }

    public int calc(final Person person) {
        int personIncome = person.getIncome();

        logger.info("bean [{}]: Calculating tax information for person id [{}]. Income: {}", beanName, person.getId(), personIncome);
        TaxLimit taxLimit = taxLimits
                .stream()
                .filter(taxLimits -> taxLimits.getLowerBoundIncome() < personIncome)
                .findFirst()
                .orElseGet(() -> new TaxLimit(0, 0));

        logger.debug("Person tax is matching the next tax limit: " + taxLimit);

        int taxDeduction = (taxLimit.getTaxPercent() * personIncome) / 100;

        logger.debug("Person tax is set to {}", taxDeduction);
        if (Gender.FEMALE.equals(person.getGender())) {
            logger.debug("Person gender is FEMALE. reducing her taxes with {}", taxDeduction, boost);
            taxDeduction -= boost;
            taxDeduction = Math.max(0, taxDeduction);
            logger.debug("final tax is {}", taxDeduction);
        }

        return taxDeduction;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
