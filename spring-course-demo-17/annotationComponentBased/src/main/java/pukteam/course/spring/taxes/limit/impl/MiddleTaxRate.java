package pukteam.course.spring.taxes.limit.impl;

import org.springframework.stereotype.Component;
import pukteam.course.spring.taxes.limit.TaxLimit;

@Component
public class MiddleTaxRate extends TaxLimit {

    public MiddleTaxRate() {
        super(15000, 25);
    }
}
