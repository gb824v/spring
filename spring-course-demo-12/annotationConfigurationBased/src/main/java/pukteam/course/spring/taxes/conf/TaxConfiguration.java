package pukteam.course.spring.taxes.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pukteam.course.spring.taxes.calculator.TaxCalculator;
import pukteam.course.spring.taxes.calculator.TaxCalculatorFactory;
import pukteam.course.spring.taxes.calculator.TaxLimit;
import pukteam.course.spring.taxes.model.Gender;
import pukteam.course.spring.taxes.model.Person;
import pukteam.course.spring.taxes.model.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class TaxConfiguration {

    @Bean
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

    @Bean
    public TaxCalculator taxCalculator() {
        return TaxCalculatorFactory.obtainFactory(1000).obtainCalculator(taxLimits());
    }

    @Bean
    public Person menash() {
        Person person = new Person(1, 4000);
        person.setAge(34);
        person.setGender(Gender.MALE);
        person.setStatus(Status.SINGLE_PARENT);
        return person;
    }

    @Bean
    public Person tikva() {
        Person person = new Person(2, 40000);
        person.setAge(25);
        person.setGender(Gender.FEMALE);
        person.setStatus(Status.SINGLE);
        return person;
    }

    @Bean
    public Person simcha() {
        Person person = new Person(3, 40000);
        person.setAge(30);
        person.setGender(Gender.OTHER);
        person.setStatus(Status.MARRIED);
        return person;
    }

    @Bean
    public Map<String, Person> personMap() {
        Map<String, Person> persons = new HashMap<>();
        persons.put("menash", menash());
        persons.put("tikva", tikva());
        persons.put("simcha", simcha());
        return persons;
    }

}
