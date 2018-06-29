package pukteam.course.spring.taxes.calculator;

import java.util.List;

public class TaxCalculatorFactory {

    private int femaleBoost;

    private TaxCalculatorFactory(int femaleBoost) {
        this.femaleBoost = femaleBoost;
    }

    public static TaxCalculatorFactory obtainFactory(int boost) {
        return new TaxCalculatorFactory(boost);
    }

    public TaxCalculator obtainCalculator(List<TaxLimit> taxLimits) {
        return new TaxCalculatorImpl(taxLimits, femaleBoost);
    }
}
