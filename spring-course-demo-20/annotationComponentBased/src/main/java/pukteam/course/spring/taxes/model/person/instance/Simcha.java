package pukteam.course.spring.taxes.model.person.instance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pukteam.course.spring.taxes.model.Gender;
import pukteam.course.spring.taxes.model.person.Person;
import pukteam.course.spring.taxes.model.Status;

import javax.annotation.PostConstruct;

@Component
public class Simcha extends Person {

    @Value("${simcha.age}")
    private int age;

    @Value("${simcha.gender}")
    private Gender gender;

    @Value("${simcha.status}")
    private Status status;

    @Value("${simcha.income}")
    private int income;

    public Simcha() {
        super(3, "simcha");
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
