package pukteam.course.spring.taxes.model.person.instance;

import org.springframework.stereotype.Component;
import pukteam.course.spring.taxes.model.Gender;
import pukteam.course.spring.taxes.model.person.Person;
import pukteam.course.spring.taxes.model.Status;

@Component
public class Simcha extends Person {

    public Simcha() {
        super(3, "simcha", 40000);
        this.setAge(25);
        this.setGender(Gender.FEMALE);
        this.setStatus(Status.SINGLE);
    }

}
