package pukteam.course.spring.taxes.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pukteam.course.spring.taxes.calculator.TaxCalculator;
import pukteam.course.spring.taxes.calculator.TaxCalculatorFactory;
import pukteam.course.spring.taxes.calculator.TaxLimit;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Import(PersonsConfiguration.class)
public class TaxConfiguration {

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

    @Bean(name="taxes-calc")
    public TaxCalculator taxCalculator() {
        return TaxCalculatorFactory.obtainFactory(1000).obtainCalculator(taxLimits());
    }
}
