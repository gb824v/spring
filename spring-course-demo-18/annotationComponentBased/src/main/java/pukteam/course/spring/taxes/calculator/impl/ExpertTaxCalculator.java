package pukteam.course.spring.taxes.calculator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pukteam.course.spring.taxes.limit.repository.impl.FullTaxLimitRepository;

@Component
public class ExpertTaxCalculator extends AbstractTaxCalculator {

    @Autowired
    public ExpertTaxCalculator(FullTaxLimitRepository fullTaxLimitRepository) {
        super(fullTaxLimitRepository);
    }
}
