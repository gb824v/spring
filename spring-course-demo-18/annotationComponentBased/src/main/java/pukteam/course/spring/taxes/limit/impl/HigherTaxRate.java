package pukteam.course.spring.taxes.limit.impl;

import org.springframework.stereotype.Component;
import pukteam.course.spring.taxes.limit.TaxLimit;

@Component
public class HigherTaxRate extends TaxLimit {

    public HigherTaxRate() {
        super(20000, 30);
    }
}
