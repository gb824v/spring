package pukteam.course.spring.taxes.model.person.instance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pukteam.course.spring.taxes.model.Gender;
import pukteam.course.spring.taxes.model.person.Person;
import pukteam.course.spring.taxes.model.Status;

import javax.annotation.PostConstruct;

@Component
public class Tikva extends Person {

    @Value("${tikva.age}")
    private int age;

    @Value("${tikva.gender}")
    private Gender gender;

    @Value("${tikva.status}")
    private Status status;

    @Value("${tikva.income}")
    private int income;

    public Tikva() {
        super(2, "tikva");
    }

    @PostConstruct
    public void init() {
        this.setIncome(income);
        this.setAge(age);
        this.setGender(gender);
        this.setStatus(status);
        super.init();
    }

}
