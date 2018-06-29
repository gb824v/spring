package pukteam.course.spring.taxes.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pukteam.course.spring.taxes.calculator.TaxCalculator;
import pukteam.course.spring.taxes.calculator.TaxCalculatorFactory;
import pukteam.course.spring.taxes.calculator.TaxLimit;
import pukteam.course.spring.taxes.model.PersonMapBeansPostProcessor;

import java.util.Collections;
import java.util.List;

@Configuration
@Import({PersonsConfiguration.class, TaxRateConfiguration.class})
public class TaxConfiguration {

    @Autowired
    @Qualifier("middleTaxRate")
    private TaxLimit lowRate;

    @Bean(name="taxes-calc-light")
    public TaxCalculator taxCalculatorSmall() {
        return TaxCalculatorFactory.obtainFactory(1000).obtainCalculator(Collections.singletonList(lowRate));
    }

    @Bean(name="taxes-calc-expert")
    public TaxCalculator taxCalculatorBig(List<TaxLimit> taxLimits) {
        return TaxCalculatorFactory.obtainFactory(1000).obtainCalculator(taxLimits);
    }

    @Bean
    public PersonMapBeansPostProcessor personMapBeansPostProcessor() {
        return new PersonMapBeansPostProcessor();
    }
}
