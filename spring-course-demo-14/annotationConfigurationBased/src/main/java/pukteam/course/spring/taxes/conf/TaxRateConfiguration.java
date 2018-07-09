package pukteam.course.spring.taxes.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import pukteam.course.spring.taxes.calculator.TaxLimit;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ImportResource("classpath:app-context.xml")
public class TaxRateConfiguration {

    @Bean
    public TaxLimit middleTaxRate() {
        return new TaxLimit(15000, 25);
    }

    @Bean
    public List<TaxLimit> taxLimits(@Qualifier("lowerTaxRate") TaxLimit lower, @Qualifier("upperTaxRate") TaxLimit upper) {
        List<TaxLimit> taxLimits = new ArrayList<>();
        taxLimits.add(lower);
        taxLimits.add(middleTaxRate());
        taxLimits.add(upper);
        return taxLimits;
    }

}
