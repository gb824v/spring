package pukteam.course.spring.taxes.model.person;

import org.springframework.stereotype.Component;
import pukteam.course.spring.taxes.model.Counter;
import pukteam.course.spring.taxes.model.Gender;
import pukteam.course.spring.taxes.model.Person;
import pukteam.course.spring.taxes.model.Status;

@Component
public class Menash extends Person {

    //@Autowired
    public Menash() {
        super(1, 4000);
        this.setAge(25);
        this.setGender(Gender.FEMALE);
        this.setStatus(Status.SINGLE);
        this.setCounter(new Counter());
    }
}
