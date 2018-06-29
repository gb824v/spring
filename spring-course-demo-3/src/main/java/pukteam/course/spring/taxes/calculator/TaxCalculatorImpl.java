package pukteam.course.spring.taxes.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pukteam.course.spring.taxes.model.Person;

import java.util.*;

public class TaxCalculatorImpl implements TaxCalculator {

    private static Logger logger = LogManager.getLogger(TaxCalculatorImpl.class);
    private Set<TaxLimit> taxLimits;

    public TaxCalculatorImpl(TaxLimit lower, TaxLimit upper) {
        taxLimits = new TreeSet<>();
        taxLimits.add(lower);
        taxLimits.add(upper);
    }

    public int calc(final Person person) {
        int personIncome = person.getIncome();

        logger.info("Calculating tax information for person id [{}]. Income: {}", person.getId(), personIncome);
        TaxLimit taxLimit = taxLimits
                .stream()
                .filter(taxLimits -> taxLimits.getLowerBoundIncome() < personIncome)
                .findFirst()
                .orElseGet(() -> new TaxLimit(0, 0));

        logger.debug("Person tax is matching the next tax limit: " + taxLimit);
        return (taxLimit.getTaxPercent() * personIncome) / 100;
    }
}
