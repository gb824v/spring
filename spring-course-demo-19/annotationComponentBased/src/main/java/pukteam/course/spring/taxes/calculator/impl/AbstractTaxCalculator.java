package pukteam.course.spring.taxes.calculator.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.BeanNameAware;
import pukteam.course.spring.taxes.calculator.TaxCalculator;
import pukteam.course.spring.taxes.limit.TaxLimit;
import pukteam.course.spring.taxes.limit.repository.TaxLimitRepository;
import pukteam.course.spring.taxes.model.Gender;
import pukteam.course.spring.taxes.model.person.Person;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public abstract class AbstractTaxCalculator implements TaxCalculator, BeanNameAware {

    private static Logger logger = LogManager.getLogger(AbstractTaxCalculator.class);

    private int boost;
    private String beanName;
    private TaxLimitRepository taxLimitRepository;
    private int electionDiscount;

    public AbstractTaxCalculator(TaxLimitRepository taxLimitRepository) {
        this.taxLimitRepository = taxLimitRepository;
    }

    @PostConstruct
    public void init() {
        if (taxLimitRepository.size() > 1 && Math.random() > 0.5 ) {
            electionDiscount = 100;
        } else {
            electionDiscount = 0;
        }
        logger.info("Init of Tax calculator. Discount is set to {}", electionDiscount);
    }

    @PreDestroy
    public void destroy() {
        logger.info("destroying tax calculator...");
    }

    public void setBoost(int boost) {
        this.boost = boost;
    }

    public int calc(final Person person) {

        if (person.getAge() < 18 ) {
            logger.warn("Not calculating taxes for minors...");
            return 0;
        }

        int personIncome = person.getIncome();
        logger.info("bean [{}]: Calculating tax information for person id [{}]. Income: {}", beanName, person.getId(), personIncome);
        TaxLimit taxLimit = taxLimitRepository
                .stream()
                .filter(taxLimits -> taxLimits.getBoundedIncome() < personIncome)
                .findFirst()
                .orElseGet(() -> new TaxLimit(0, 0));

        logger.debug("Person tax is matching the next tax limit: " + taxLimit);

        int taxDeduction = (taxLimit.getTaxPercent() * personIncome) / 100;

        logger.debug("Person tax is set to {}", taxDeduction);
        if (Gender.FEMALE.equals(person.getGender())) {
            logger.debug("Person gender is FEMALE. reducing her taxes with {} (was originally {})", boost, taxDeduction);
            taxDeduction -= boost;
            taxDeduction = Math.max(0, taxDeduction);
        }

        if (electionDiscount > 0 && taxDeduction > electionDiscount) {
            taxDeduction -= electionDiscount;
            logger.info("It's your lucky day ! due to election promise another {} were deduced from your taxes !", electionDiscount);
        }

        logger.debug("final tax is {}", taxDeduction);
        return taxDeduction;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

}
