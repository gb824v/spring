package pukteam.course.spring.taxes.calculator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pukteam.course.spring.taxes.limit.repository.impl.LightTaxLimitRepository;

@Component
public class LightTaxCalculator extends AbstractTaxCalculator {

    @Autowired
    public LightTaxCalculator(LightTaxLimitRepository lightTaxLimitRepository) {
        super(lightTaxLimitRepository);
    }
}
