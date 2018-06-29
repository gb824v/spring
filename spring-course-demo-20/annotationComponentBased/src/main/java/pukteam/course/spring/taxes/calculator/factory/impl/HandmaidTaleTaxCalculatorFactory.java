package pukteam.course.spring.taxes.calculator.factory.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HandmaidTaleTaxCalculatorFactory extends AbstractTaxCalculatorFactory {

    @Value("${handmaid.calc.boost}")
    private int femaleBoost;

    @PostConstruct
    private void init() {
        lightTaxCalculator.setBoost(femaleBoost);
        expertTaxCalculator.setBoost(femaleBoost);
    }

}
