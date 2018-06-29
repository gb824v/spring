package pukteam.course.spring.taxes.calculator.factory.impl;

import org.springframework.stereotype.Component;

@Component
public class HandmaidTaleTaxCalculatorFactory extends AbstractTaxCalculatorFactory {

    protected HandmaidTaleTaxCalculatorFactory() {
        super(0);
    }
}
