package pukteam.course.spring.taxes.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import pukteam.course.spring.taxes.model.Counter;
import pukteam.course.spring.taxes.model.Gender;
import pukteam.course.spring.taxes.model.Person;
import pukteam.course.spring.taxes.model.Status;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PersonsConfiguration {

    @Bean(initMethod = "initializationValidation", destroyMethod = "destruction")
    public Person menash() {
        Person person = new Person(1, 4000);
        person.setAge(34);
        person.setGender(Gender.MALE);
        person.setStatus(Status.SINGLE_PARENT);
        person.setCounter(counter());
        return person;
    }

    @Bean(initMethod = "initializationValidation", destroyMethod = "destruction")
    public Person tikva() {
        Person person = new Person(2, 40000);
        person.setAge(25);
        person.setGender(Gender.FEMALE);
        person.setStatus(Status.SINGLE);
        person.setCounter(counter());
        return person;
    }

    @Bean(initMethod = "initializationValidation", destroyMethod = "destruction")
    public Person simcha() {
        Person person = new Person(3, 40000);
        person.setAge(30);
        person.setGender(Gender.OTHER);
        person.setStatus(Status.MARRIED);
        person.setCounter(counter());
        return person;
    }

    @Bean
    @Scope(value="prototype")
    public Counter counter() {
        return new Counter();
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
