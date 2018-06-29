package pukteam.course.spring.taxes.limit.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pukteam.course.spring.taxes.limit.TaxLimit;

import javax.annotation.PostConstruct;

@Component
public class LowerTaxRate extends TaxLimit {

    @Value("${lower.taxlimit.bound.income}")
    private int incomeBound;

    @Value("${lower.taxlimit.bound.percent}")
    private int percent;

    @PostConstruct
    public void init() {
        setBoundedIncome(incomeBound);
        setTaxPercent(percent);
    }
}
