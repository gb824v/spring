package pukteam.course.spring.taxes.limit.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pukteam.course.spring.taxes.limit.TaxLimit;

import javax.annotation.PostConstruct;

@Component
public class MiddleTaxRate extends TaxLimit {

    @Value("${middle.taxlimit.bound.income}")
    private int incomeBound;

    @Value("${middle.taxlimit.bound.percent}")
    private int percent;

    @PostConstruct
    public void init() {
        setBoundedIncome(incomeBound);
        setTaxPercent(percent);
    }
}
