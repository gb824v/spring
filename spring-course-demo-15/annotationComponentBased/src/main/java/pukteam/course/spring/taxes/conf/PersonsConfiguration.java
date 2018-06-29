package pukteam.course.spring.taxes.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import pukteam.course.spring.taxes.model.Counter;
import pukteam.course.spring.taxes.model.Gender;
import pukteam.course.spring.taxes.model.Person;
import pukteam.course.spring.taxes.model.Status;
import pukteam.course.spring.taxes.model.person.Menash;
import pukteam.course.spring.taxes.model.person.Simcha;
import pukteam.course.spring.taxes.model.person.Tikva;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("pukteam.course.spring.taxes.model.person")
public class PersonsConfiguration {

    @Autowired
    private Tikva tikva;

    @Autowired
    private Menash menash;

    @Autowired
    private Simcha simcha;

    @Bean
    public Map<String, Person> personMap() {
        Map<String, Person> persons = new HashMap<>();
        persons.put("menash", menash);
        persons.put("tikva", tikva);
        persons.put("simcha", simcha);
        return persons;
    }

}
