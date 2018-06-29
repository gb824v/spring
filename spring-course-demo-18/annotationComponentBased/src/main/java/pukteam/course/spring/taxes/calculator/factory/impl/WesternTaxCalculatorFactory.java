package pukteam.course.spring.taxes.calculator.factory.impl;

import org.springframework.stereotype.Component;

@Component
public class WesternTaxCalculatorFactory extends AbstractTaxCalculatorFactory {

    private WesternTaxCalculatorFactory() {
        super(1000);
    }

}
