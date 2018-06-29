package pukteam.course.spring.taxes.calculator.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pukteam.course.spring.taxes.calculator.impl.ExpertTaxCalculator;
import pukteam.course.spring.taxes.calculator.impl.LightTaxCalculator;
import pukteam.course.spring.taxes.calculator.TaxCalculator;
import pukteam.course.spring.taxes.calculator.factory.TaxCalculatorFactory;
import pukteam.course.spring.taxes.calculator.factory.TaxCalculatorMode;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

public abstract class AbstractTaxCalculatorFactory implements TaxCalculatorFactory {

    private int femaleBoost;

    @Resource
    private LightTaxCalculator lightTaxCalculator;
    
    @Resource
    private ExpertTaxCalculator expertTaxCalculator;

    protected AbstractTaxCalculatorFactory(int femaleBoost) {
        this.femaleBoost = femaleBoost;
    }

    @PostConstruct
    private void init() {
        lightTaxCalculator.setBoost(femaleBoost);
        expertTaxCalculator.setBoost(femaleBoost);
    }

    @Override
    public TaxCalculator obtainCalculator(TaxCalculatorMode mode) {
        switch (mode) {
            case FULL:
                return expertTaxCalculator;
            case LIGHT:
                return lightTaxCalculator;
            default:
                throw new UnsupportedOperationException("Error ! no matching calculator for " + mode);
        }
    }

}
