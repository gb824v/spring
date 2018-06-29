package pukteam.course.spring.taxes.calculator.factory;

import pukteam.course.spring.taxes.calculator.TaxCalculator;

public interface TaxCalculatorFactory {
    TaxCalculator obtainCalculator(TaxCalculatorMode mode);
}
