package pukteam.course.spring.taxes.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pukteam.course.spring.taxes.model.Gender;
import pukteam.course.spring.taxes.model.Person;

import java.util.*;

public class TaxCalculatorImpl implements TaxCalculator {

    private static Logger logger = LogManager.getLogger(TaxCalculatorImpl.class);
    private List<TaxLimit> taxLimits;
    private int boost;

    TaxCalculatorImpl(List<TaxLimit> taxLimits, int boost) {
        this.taxLimits = taxLimits;
        this.boost = boost;
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
}
