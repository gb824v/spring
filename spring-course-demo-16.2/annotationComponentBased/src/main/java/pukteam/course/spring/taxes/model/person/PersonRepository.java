package pukteam.course.spring.taxes.model.person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.stream.Stream;

@Component
public class PersonRepository {

    private static Logger logger = LogManager.getLogger(PersonRepository.class);

    @Autowired
    private Set<Person> persons;
    
    @PostConstruct
    void init() {
        if (persons == null || persons.size() == 0) {
            logger.error("Error on persons repository ! It seems that there are no defined persons");
        }
    }

    public Stream<Person> stream() {
        return persons.stream();
    }
}
