package pukteam.course.spring.taxes.limit.impl;

import org.springframework.stereotype.Component;
import pukteam.course.spring.taxes.limit.TaxLimit;

@Component
public class LowerTaxRate extends TaxLimit {

    public LowerTaxRate() {
        super(10000, 20);
    }

}
