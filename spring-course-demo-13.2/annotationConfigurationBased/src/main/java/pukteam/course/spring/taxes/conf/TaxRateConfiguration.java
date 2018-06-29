package pukteam.course.spring.taxes.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pukteam.course.spring.taxes.calculator.TaxLimit;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TaxRateConfiguration {

    @Bean(name="low")
    public TaxLimit lowerTaxRate() {
        return new TaxLimit(10000, 20);
    }

    @Bean
    public TaxLimit higherTaxRate() {
        return new TaxLimit(20000, 30);
    }

    @Bean
    public TaxLimit middleTaxRate() {
        return new TaxLimit(15000, 25);
    }

    @Bean
    public List<TaxLimit> taxLimits() {
        List<TaxLimit> taxLimits = new ArrayList<>();
        taxLimits.add(lowerTaxRate());
        taxLimits.add(middleTaxRate());
        taxLimits.add(higherTaxRate());
        return taxLimits;
    }

}
